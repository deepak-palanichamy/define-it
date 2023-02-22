package dev.datapirate.dictionary.controller;

import dev.datapirate.dictionary.entity.BaseDefinitionResponse;
import dev.datapirate.dictionary.service.api.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/definitions")
    public ResponseEntity<BaseDefinitionResponse> getDefinition(@RequestParam(value = "word") String word) throws IOException, InterruptedException {

        BaseDefinitionResponse definition = dictionaryService.getDefinition(word);

        return ResponseEntity.ok().body(definition);
    }

}
