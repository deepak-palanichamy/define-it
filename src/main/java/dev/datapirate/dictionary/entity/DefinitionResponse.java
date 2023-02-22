package dev.datapirate.dictionary.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DefinitionResponse {
    private String word;
    private String phonetic;
    private Phonetic[] phonetics;
    private Meaning[] meanings;
    private License license;
    private String[] sourceUrls;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Phonetic {
    private String text;
    private String audio;
    private String sourceUrl;
    private License license;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class License {
    private String name;
    private String url;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Meaning {
    private String partOfSpeech;
    private Definition[] definitions;
    private String[] synonyms;
    private String[] antonyms;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Definition {
    private String definition;
    private String[] synonyms;
    private String[] antonyms;
    private String example;
}
