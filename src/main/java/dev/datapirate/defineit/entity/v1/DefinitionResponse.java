package dev.datapirate.defineit.entity.v1;

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
class License {
    private String name;
    private String url;
}

