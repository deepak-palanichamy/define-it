package dev.datapirate.defineit.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.datapirate.defineit.model.v1.*;
import dev.datapirate.defineit.model.v2.ConsolidatedDefinitionV2;
import dev.datapirate.defineit.model.v2.DefinitionV2;
import dev.datapirate.defineit.model.v2.MeaningV2;
import dev.datapirate.defineit.model.v2.PhoneticV2;
import dev.datapirate.defineit.exception.DefinitionNotFoundException;
import dev.datapirate.defineit.service.api.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final ObjectMapper objectMapper;

    @Override
    public ConsolidatedDefinitionV2 getDefinition(String word) throws DefinitionNotFoundException, IOException, InterruptedException {
        log.info("Entering getDefinition(), word: {}", word);
        if (word.isBlank()) {
            throw new IllegalArgumentException("Word cannot be blank");
        }
        word = word.replace(" ", "%20");

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.dictionaryapi.dev/api/v2/entries/en/" + word))
                .timeout(Duration.ofSeconds(10))
                .build();
        HttpResponse<String> httpResponse = HttpClient.newBuilder()
                .build()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseBody = httpResponse.body();
        Response response = new Response();
        ConsolidatedDefinitionV2 consolidatedDefinitionV2 = new ConsolidatedDefinitionV2();
        try {
            DefinitionResponse[] definitionResponse = objectMapper.readValue(responseBody, DefinitionResponse[].class);
            response.setDefinition(definitionResponse);

            consolidatedDefinitionV2 = consolidateDefinition(definitionResponse);

            log.info("Leaving getDefinition(), response: {}", response);
            return consolidatedDefinitionV2;
        } catch (JsonProcessingException e) {
            UnknownDefinitionResponse unknownDefinitionResponse = objectMapper.readValue(responseBody, UnknownDefinitionResponse.class);
            response.setError(unknownDefinitionResponse);
        }
        if (response.getError() != null) {
            throw new DefinitionNotFoundException("No Definition(s) Found for word: " + word.replace("%20", " "), HttpStatus.NOT_FOUND);
        }

        log.info("Leaving getDefinition(), response: {}", response);
        return consolidatedDefinitionV2;
    }

    private ConsolidatedDefinitionV2 consolidateDefinition(DefinitionResponse[] definitionResponses) {
        log.info("Entering consolidateDefinition(), # of definitionResponses: {}", definitionResponses.length);

        var words = new HashSet<String>(definitionResponses.length);
        var phonetics = new HashMap<String, PhoneticV2>();
        var meanings = new HashMap<String, MeaningV2>();

        for (DefinitionResponse definitionResponse : definitionResponses) {

            // consolidate words
            words.add(definitionResponse.getWord());
            // consolidate phonetics
            Map<String, PhoneticV2> phoneticMap = Arrays.stream(definitionResponse.getPhonetics()).collect(Collectors.toMap(
                    Phonetic::getText, phonetic -> new PhoneticV2(phonetic.getText(), phonetic.getAudio()), (text1, text2) -> {
                        log.debug("Ignoring duplicate phonetic '{}'", text1);
                        return text1;
                    }
            ));
            phonetics.putAll(phoneticMap);
            // consolidate meanings
            Map<String, MeaningV2> meaningMap = Arrays.stream(definitionResponse.getMeanings()).collect(Collectors.toMap(
                    Meaning::getPartOfSpeech, meaning -> {
                        List<DefinitionV2> definitions = Arrays.stream(meaning.getDefinitions()).map(
                                definition -> new DefinitionV2(definition.getDefinition(), definition.getExample())
                        ).collect(Collectors.toList());
                        List<String> synonyms = List.of(meaning.getSynonyms());
                        List<String> antonyms = List.of(meaning.getAntonyms());
                        return new MeaningV2(meaning.getPartOfSpeech(), definitions, synonyms, antonyms);
                    },
                    MeaningV2::merge
            ));
            meanings.putAll(meaningMap);
        }

        ConsolidatedDefinitionV2 consolidatedDefinitionV2 = new ConsolidatedDefinitionV2();
        consolidatedDefinitionV2.setWord(words);
        consolidatedDefinitionV2.setPhonetics(phonetics);
        consolidatedDefinitionV2.setMeanings(meanings);

        log.info("Leaving consolidateDefinition(), consolidatedDefinitionV2: {}", consolidatedDefinitionV2);
        return consolidatedDefinitionV2;
    }
}
