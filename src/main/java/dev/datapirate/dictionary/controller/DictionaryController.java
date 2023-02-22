package dev.datapirate.dictionary.controller;

import dev.datapirate.dictionary.entity.Response;
import dev.datapirate.dictionary.service.api.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @PostMapping("/definitions")
    public ResponseEntity<Response> getDefinition(@RequestParam(value = "word") String word) throws IOException, InterruptedException {
        log.info("Entering getDefinition(), word: {}", word);
        Response definition = dictionaryService.getDefinition(word);
        log.info("Leaving getDefinition(), definition: {}", definition);
        return ResponseEntity.ok().body(definition);
    }

}
