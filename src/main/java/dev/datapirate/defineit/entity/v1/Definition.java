package dev.datapirate.defineit.entity.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Definition {
    private String definition;
    private String[] synonyms;
    private String[] antonyms;
    private String example;
}
