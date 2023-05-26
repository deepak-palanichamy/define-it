package dev.datapirate.defineit.model.v2;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class ConsolidatedDefinitionV2 {
    private String word;
    private ArrayList<PhoneticV2> phonetics;
    private HashMap<String, MeaningV2> meanings;
//    private HashMap<String, List<String>> usageExamples;
//    private HashMap<String, List<SimilarWords>> similarWords;
}

