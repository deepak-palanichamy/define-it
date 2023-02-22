package dev.datapirate.dictionary.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnknownDefinitionResponse {
    private String title;
    private String message;
    private String resolution;
}
