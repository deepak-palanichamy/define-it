package dev.datapirate.defineit.service.api;

import dev.datapirate.defineit.entity.v2.ConsolidatedDefinitionV2;

public interface DictionaryService {
    ConsolidatedDefinitionV2 getDefinition(String word) throws Exception;
}
