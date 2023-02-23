package dev.datapirate.dictionary.controller;

import dev.datapirate.dictionary.entity.v2.ConsolidatedDefinitionV2;
import dev.datapirate.dictionary.service.api.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/definitions")
    public ConsolidatedDefinitionV2 getDefinition(@RequestParam(value = "word") String word) throws Exception {
        log.info("Entering getDefinition(), word: {}", word);
        ConsolidatedDefinitionV2 definition = dictionaryService.getDefinition(word);
        log.info("Leaving getDefinition(), definition: {}", definition);
        return definition;
    }

}
