package com.example.aggregator.service;

import com.example.aggregator.client.AggregatorRestClient;
import com.example.aggregator.model.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AggregatorService {

    private AggregatorRestClient aggregatorRestClient;

    public AggregatorService(AggregatorRestClient aggregatorRestClient) {
        this.aggregatorRestClient = aggregatorRestClient;
    }

    public Entry getDefinitionFor(String word) {
        return aggregatorRestClient.getDefinitionFor(word);
    }

    public List<Entry> getWordsStartingWith(String chars) {
        return aggregatorRestClient.getWordsStartingWith(chars);
    }

    public List<Entry> getWordsThatContain(String chars) {
        return aggregatorRestClient.getWordsThatContain(chars);
    }

    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(String chars) {

        List<Entry> wordsThatStartWith = aggregatorRestClient.getWordsStartingWith(chars);
        List<Entry> wordsThatContainSuccessiveLetters = aggregatorRestClient.getWordsThatContainConsecutiveLetters();

        List<Entry> common = new ArrayList<>(wordsThatStartWith);
        common.retainAll(wordsThatContainSuccessiveLetters);

        return common;
    }

    public List<Entry> getWordsThatContainSpecificConsecutiveLetters(String chars) {

        List<Entry> wordsThatContainSuccessiveLetters = aggregatorRestClient.getWordsThatContainConsecutiveLetters();

        List<Entry> common = new ArrayList<>(wordsThatContainSuccessiveLetters);
        common.removeIf(entry -> !entry.getWord().contains(chars));

        return common;
    }


    //Extra Credit
    // New method to get all palindromes
    public List<Entry> getAllPalindromes() {
        final List<Entry> candidates = new ArrayList<>();

        // Iterate from 'a' to 'z'
        for (char c = 'a'; c <= 'z'; c++) {
            String character = String.valueOf(c);

            // Get words starting and ending with the character
            List<Entry> startsWith = aggregatorRestClient.getWordsStartingWith(character);
            List<Entry> endsWith = aggregatorRestClient.getWordsEndingWith(character);

            // Keep entries that exist in both lists (words that start and end with the same letter)
            List<Entry> startsAndEndsWith = new ArrayList<>();
            for (Entry startEntry : startsWith) {
                for (Entry endEntry : endsWith) {
                    if (startEntry.getWord().equals(endEntry.getWord())) {
                        startsAndEndsWith.add(startEntry);
                        break; // No need to continue searching once we find a match
                    }
                }
            }

            // Add valid words to the candidates list
            candidates.addAll(startsAndEndsWith);
        }

        // Now filter out the palindromes
        List<Entry> palindromes = new ArrayList<>();
        for (Entry entry : candidates) {
            String word = entry.getWord();
            if (isPalindrome(word)) {
                palindromes.add(entry);
            }
        }

        // Sort the palindrome entries alphabetically
        palindromes.sort((entry1, entry2) -> entry1.getWord().compareTo(entry2.getWord()));

        return palindromes;
    }

    // Helper method to check if a word is a palindrome
    private boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false; // Not a palindrome
            }
            left++;
            right--;
        }

        return true; // Word is a palindrome
    }
}