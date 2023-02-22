package dev.datapirate.dictionary.entity.v2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneticV2 {
    private String text;
    private String audio;
}
