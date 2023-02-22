package dev.datapirate.dictionary.service.api;

import dev.datapirate.dictionary.entity.v1.Response;

import java.io.IOException;

public interface DictionaryService {
    Response getDefinition(String word) throws IOException, InterruptedException;
}
