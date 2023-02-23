package dev.datapirate.dictionary.service.api;

import dev.datapirate.dictionary.entity.v2.ConsolidatedDefinitionV2;

public interface DictionaryService {
    ConsolidatedDefinitionV2 getDefinition(String word) throws Exception;
}
