package dev.datapirate.dictionary.service.api;

import dev.datapirate.dictionary.entity.BaseDefinitionResponse;

import java.io.IOException;

public interface DictionaryService {
    BaseDefinitionResponse getDefinition(String word) throws IOException, InterruptedException;
}
