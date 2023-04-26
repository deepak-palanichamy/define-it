package dev.datapirate.defineit.model.v2;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;

@Data
public class ConsolidatedDefinitionV2 {
    private HashSet<String> word;
    private HashMap<String, PhoneticV2> phonetics;
    private HashMap<String, MeaningV2> meanings;
}

