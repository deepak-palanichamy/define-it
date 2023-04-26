package dev.datapirate.defineit.controller;

import dev.datapirate.defineit.model.ApiResponse;
import dev.datapirate.defineit.model.v2.ConsolidatedDefinitionV2;
import dev.datapirate.defineit.service.api.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/definitions")
public class DefinitionsController {

    private final DictionaryService dictionaryService;

    @GetMapping
    public ResponseEntity<ApiResponse<ConsolidatedDefinitionV2>> getDefinition(@RequestParam(value = "word") String word) throws Exception {
        log.info("Entering getDefinition(), word: {}", word);
        ConsolidatedDefinitionV2 definition = dictionaryService.getDefinition(word);
        log.info("Leaving getDefinition(), definition: {}", definition);
        return new ResponseEntity<>(new ApiResponse<>(true, definition), HttpStatus.OK);
    }

}
