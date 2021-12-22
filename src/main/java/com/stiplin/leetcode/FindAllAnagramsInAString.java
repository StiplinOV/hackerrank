package com.stiplin.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        FindAllAnagramsInAString f = new FindAllAnagramsInAString();
        System.out.println(f.findAnagrams("cbaebabacd", "abc"));

    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> symbolsMap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            symbolsMap.merge(p.charAt(i), 1, Integer::sum);
        }
        int leftPointer = 0;
        int rightPointer = 0;
        Map<Character, Integer> slidingWindow = new HashMap<>();
        while (rightPointer < s.length()) {
            char symbol = s.charAt(rightPointer);
            if (!symbolsMap.containsKey(symbol)) {
                leftPointer = rightPointer + 1;
                rightPointer = leftPointer;
                slidingWindow = new HashMap<>();
            } else {
                int currentFreq = slidingWindow.getOrDefault(symbol, 0);
                int freq = symbolsMap.get(symbol);
                while (currentFreq >= freq) {
                    slidingWindow.merge(s.charAt(leftPointer), -1, Integer::sum);
                    leftPointer++;
                    currentFreq = slidingWindow.get(symbol);
                }
                slidingWindow.merge(symbol, 1, Integer::sum);
                if (slidingWindow.equals(symbolsMap)) {
                    result.add(leftPointer);
                }
                rightPointer++;
            }
        }
        return result;
    }
}
