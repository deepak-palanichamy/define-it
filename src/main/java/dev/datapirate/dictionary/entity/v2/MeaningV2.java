package dev.datapirate.dictionary.entity.v2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MeaningV2 {
    private String partOfSpeech;
    private List<DefinitionV2> definitions;
    private List<String> synonyms;
    private List<String> antonyms;
}
