package com.stiplin.leetcode;

import java.util.*;

public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> leftPointers = new HashMap<>();
        Map<Character, Integer> rightPointers = new HashMap<>();
        TreeMap<Integer, Integer> intervals = new TreeMap<>();
        TreeMap<Integer, Integer> resultIntervals = new TreeMap<>();
        for(int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (!leftPointers.containsKey(symbol)) {
                leftPointers.put(symbol, i);
            }
        }
        for(int i = s.length() - 1; i >= 0; i--) {
            char symbol = s.charAt(i);
            if (!rightPointers.containsKey(symbol)) {
                rightPointers.put(symbol, i);
            }
        }
        for (char symbol: leftPointers.keySet()) {
            intervals.put(leftPointers.get(symbol), rightPointers.get(symbol));
        }
        Integer currentLeft = intervals.firstKey();
        Integer currentRight = intervals.get(currentLeft);
        Integer nextLeft = intervals.higherKey(currentLeft);
        while (nextLeft != null) {
            Integer nextRight = intervals.get(nextLeft);
            if (nextLeft < currentRight) {
                if (nextRight > currentRight) {
                    currentRight = nextRight;
                }
                intervals.remove(nextLeft);
                nextLeft = intervals.higherKey(currentLeft);
            } else {
                resultIntervals.put(currentLeft, currentRight);
                currentLeft = nextLeft;
                currentRight = nextRight;
                nextLeft = intervals.higherKey(currentLeft);
            }
        }
        resultIntervals.put(currentLeft, currentRight);
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: resultIntervals.entrySet()) {
            result.add(entry.getValue() - entry.getKey() + 1);
        }
        return result;
    }
}
