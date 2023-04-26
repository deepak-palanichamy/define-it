package dev.datapirate.defineit.entity.v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
public class MeaningV2 {
    private String partOfSpeech;
    private List<DefinitionV2> definitions;
    private List<String> synonyms;
    private List<String> antonyms;

    public MeaningV2 merge(MeaningV2 m2) {
        log.debug("Entering merge(), merging meaning: {} with other meaning: {}", this, m2);
        ArrayList<DefinitionV2> definitions = new ArrayList<>(this.getDefinitions());
        definitions.addAll(m2.getDefinitions());
        this.setDefinitions(definitions);

        ArrayList<String> synonyms = new ArrayList<>(this.getSynonyms());
        synonyms.addAll(m2.getSynonyms());
        this.setSynonyms(synonyms);

        ArrayList<String> antonyms = new ArrayList<>(this.getAntonyms());
        antonyms.addAll(m2.getAntonyms());
        this.setAntonyms(antonyms);

        log.debug("Leaving merge(), merged meaning: {}", this);
        return this;
    }
}
