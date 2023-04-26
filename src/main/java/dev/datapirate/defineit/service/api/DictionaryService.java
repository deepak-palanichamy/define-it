package dev.datapirate.defineit.service.api;

import dev.datapirate.defineit.model.v2.ConsolidatedDefinitionV2;

public interface DictionaryService {
    ConsolidatedDefinitionV2 getDefinition(String word) throws Exception;
}
