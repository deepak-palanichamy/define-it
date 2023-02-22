package dev.datapirate.dictionary.entity.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Phonetic {
    private String text;
    private String audio;
    private String sourceUrl;
    private License license;
}
