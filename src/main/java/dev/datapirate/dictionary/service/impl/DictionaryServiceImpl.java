package dev.datapirate.dictionary.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.datapirate.dictionary.entity.DefinitionResponse;
import dev.datapirate.dictionary.entity.Response;
import dev.datapirate.dictionary.entity.UnknownDefinitionResponse;
import dev.datapirate.dictionary.service.api.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final ObjectMapper objectMapper;

    @Override
    public Response getDefinition(String word) throws IOException, InterruptedException {
        log.info("Entering getDefinition(), word: {}", word);
        if (word.isBlank()) {
            throw new IllegalArgumentException("Word cannot be blank!");
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
        try {
            DefinitionResponse[] definitionResponse = objectMapper.readValue(responseBody, DefinitionResponse[].class);
            response.setDefinition(definitionResponse);
        } catch (Exception e) {
            UnknownDefinitionResponse unknownDefinitionResponse = objectMapper.readValue(responseBody, UnknownDefinitionResponse.class);
            response.setError(unknownDefinitionResponse);
        }
        log.info("Leaving getDefinition(), response: {}", response);
        return response;
    }
}
