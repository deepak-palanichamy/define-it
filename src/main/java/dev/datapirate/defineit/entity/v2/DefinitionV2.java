package dev.datapirate.defineit.entity.v2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefinitionV2 {
    private String definition;
    private String example;
}