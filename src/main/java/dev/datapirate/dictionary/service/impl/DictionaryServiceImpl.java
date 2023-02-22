package dev.datapirate.dictionary.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.datapirate.dictionary.entity.BaseDefinitionResponse;
import dev.datapirate.dictionary.entity.DefinitionResponse;
import dev.datapirate.dictionary.entity.UnknownDefinitionResponse;
import dev.datapirate.dictionary.service.api.DictionaryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Override
    public BaseDefinitionResponse getDefinition(String word) throws IOException, InterruptedException {
        word = word.replace(" ", "%20");
//        System.out.println(word);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.dictionaryapi.dev/api/v2/entries/en/" + word))
                .timeout(Duration.ofSeconds(10))
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        String responseBody = response.body();
        BaseDefinitionResponse baseDefinitionResponse = new BaseDefinitionResponse();
        try {
            DefinitionResponse[] definitionResponse = mapper.readValue(responseBody, DefinitionResponse[].class);
            System.out.println(Arrays.toString(definitionResponse));
            baseDefinitionResponse.setDefinition(definitionResponse);
        } catch (Exception e) {
            UnknownDefinitionResponse unknownDefinitionResponse = mapper.readValue(responseBody, UnknownDefinitionResponse.class);
            System.out.println(unknownDefinitionResponse);
            baseDefinitionResponse.setError(unknownDefinitionResponse);
        }
        return baseDefinitionResponse;
    }
}
