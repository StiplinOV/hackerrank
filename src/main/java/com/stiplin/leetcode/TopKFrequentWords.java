package com.stiplin.leetcode;

import java.util.*;

public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> frequentMap = new HashMap<>();
        for(String word: words) {
            frequentMap.merge(word, 1, Integer::sum);
        }
        TreeMap<Integer, TreeSet<String>> reverseMap = new TreeMap<>((l,r) -> r - l);
        for(Map.Entry<String, Integer> entry: frequentMap.entrySet()) {
            int frequent = entry.getValue();
            String word = entry.getKey();
            TreeSet<String> sortedWords = reverseMap.get(frequent);
            if (sortedWords == null) {
                sortedWords = new TreeSet<>();
            }
            sortedWords.add(word);
            reverseMap.put(frequent, sortedWords);
        }
        List<String> result = new ArrayList<>();
        Integer currentFrequent = reverseMap.firstKey();
        TreeSet<String> currentWords = reverseMap.get(currentFrequent);
        while (k != 0 && currentFrequent != null) {
            if (currentWords.isEmpty()) {
                currentFrequent = reverseMap.higherKey(currentFrequent);
                currentWords = reverseMap.get(currentFrequent);
            } else {
                String word = currentWords.first();
                currentWords.remove(word);
                result.add(word);
                k--;
            }
        }
        return result;
    }

}
