package com.stiplin.leetcode;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> firstSymbolsMap = new HashMap<>();
        Map<Character, Integer> secondSymbolsMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            firstSymbolsMap.merge(s.charAt(i), 1, Integer::sum);
        }
        for (int i = 0; i < t.length(); i++) {
            secondSymbolsMap.merge(t.charAt(i), 1, Integer::sum);
        }
        return firstSymbolsMap.equals(secondSymbolsMap);
    }
}
