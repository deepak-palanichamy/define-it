package dev.datapirate.defineit.model.v2;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SimilarWords {
    private String partsOfSpeech;
    private List<String> synonyms;
    private List<String> antonyms;
}
