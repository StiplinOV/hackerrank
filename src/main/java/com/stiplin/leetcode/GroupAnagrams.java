package com.stiplin.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Map<Character, Integer>, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            Map<Character, Integer> symbolsMap = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                symbolsMap.merge(str.charAt(i), 1, Integer::sum);
            }
            List<String> group = groups.get(symbolsMap);
            if (group == null) {
                group = new ArrayList<>();
            }
            group.add(str);
            groups.put(symbolsMap, group);
        }
        return new ArrayList<>(groups.values());
    }
}
