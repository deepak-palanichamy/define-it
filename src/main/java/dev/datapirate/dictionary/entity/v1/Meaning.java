package dev.datapirate.dictionary.entity.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Meaning {
    private String partOfSpeech;
    private Definition[] definitions;
    private String[] synonyms;
    private String[] antonyms;
}
