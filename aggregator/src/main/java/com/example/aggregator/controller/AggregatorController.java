package com.example.aggregator.controller;

import com.example.aggregator.model.Entry;
import com.example.aggregator.service.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AggregatorController {

    private static final Logger log = LoggerFactory.getLogger(AggregatorController.class.getName());

    private AggregatorService service;

    public AggregatorController(AggregatorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Entry> helloWorld() {
        List<Entry> entries = new ArrayList<>();
        entries.add(service.getDefinitionFor("hello"));
        entries.add(service.getDefinitionFor("world"));
        return entries;
    }

    @GetMapping("/getDefinitionFor/{word}")
    public Entry getDefinitionFor(@PathVariable String word) {

        StopWatch sw = new StopWatch();
        sw.start();
        Entry result = service.getDefinitionFor(word);
        sw.stop();

        long nanoSeconds = sw.getLastTaskTimeNanos();
        String message = new StringBuilder().append("Retrieved entry for [")
                .append(word)
                .append("] in ")
                .append(nanoSeconds / 1000000.0)
                .append("ms")
                .toString();
        log.info(message);
        return result;
    }

    //Adding getAllPalindromes
@GetMapping("/getAllPalindromes")
public List<Entry> getAllPalindromes() {

    StopWatch sw = new StopWatch();
    sw.start();
    List<Entry> results = service.getAllPalindromes(); // Calling the service method for palindromes
        sw.stop();

    long nanoSeconds = sw.getTotalTimeNanos();
    String message = new StringBuilder().append("Retrieved entries for palindromes, containing ")
            .append(results.size())
            .append(" entries in ")
            .append(nanoSeconds / 1000000.0)
            .append("ms")
            .toString();
        log.info(message);
        return results;
    }

    @GetMapping("/getWordsThatContainSuccessiveLettersAndStartsWith/{chars}")
    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(@PathVariable String chars) {

        StopWatch sw = new StopWatch();
        sw.start();
        List<Entry> results = service.getWordsThatContainSuccessiveLettersAndStartsWith(chars);
        sw.stop();

        long nanoSeconds = sw.getTotalTimeNanos();
        String message = new StringBuilder().append("Retrieved entries for words containing successive letters and starting with [")
                                            .append(chars)
                                            .append("], containing ")
                                            .append(results.size())
                                            .append(" entries in ")
                                            .append(nanoSeconds / 1000000.0)
                                            .append("ms")
                                            .toString();
        log.info(message);
        return results;
    }

    @GetMapping("/getWordsStartingWith/{chars}")
    public List<Entry> getWordsStartingWith(@PathVariable String chars) {

        StopWatch sw = new StopWatch();
        sw.start();
        List<Entry> results = service.getWordsStartingWith(chars);
        sw.stop();

        long nanoSeconds = sw.getTotalTimeNanos();
        String message = new StringBuilder().append("Retrieved entries for words starting with [")
                                            .append(chars)
                                            .append("], containing ")
                                            .append(results.size())
                                            .append(" entries in ")
                                            .append(nanoSeconds / 1000000.0)
                                            .append("ms")
                                            .toString();
        log.info(message);
        return results;
    }

    @GetMapping("/getWordsThatContain/{chars}")
    public List<Entry> getWordsThatContain(@PathVariable String chars) {

        StopWatch sw = new StopWatch();
        sw.start();
        List<Entry> results = service.getWordsThatContain(chars);
        sw.stop();

        long nanoSeconds = sw.getTotalTimeNanos();
        String message = new StringBuilder().append("Retrieved entries for words containing [")
                                            .append(chars)
                                            .append("], containing ")
                                            .append(results.size())
                                            .append(" entries in ")
                                            .append(nanoSeconds / 1000000.0)
                                            .append("ms")
                                            .toString();
        log.info(message);
        return results;
    }

    @GetMapping("/getWordsThatContainSpecificConsecutiveLetters/{chars}")
    public List<Entry> getWordsThatContainSpecificConsecutiveLetters(@PathVariable String chars) {

        StopWatch sw = new StopWatch();
        sw.start();
        List<Entry> results = service.getWordsThatContainSpecificConsecutiveLetters(chars);
        sw.stop();

        long nanoSeconds = sw.getTotalTimeNanos();
        String message = new StringBuilder().append("Retrieved entries for words containing specific consecutive letters [")
                                            .append(chars)
                                            .append("], containing ")
                                            .append(results.size())
                                            .append(" entries in ")
                                            .append(nanoSeconds / 1000000.0)
                                            .append("ms")
                                            .toString();
        log.info(message);
        return results;
    }
}
