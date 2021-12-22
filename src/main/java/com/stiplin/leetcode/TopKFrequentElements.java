package com.stiplin.leetcode;

import java.util.*;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequentMap = new HashMap<>();
        for (Integer num : nums) {
            frequentMap.merge(num, 1, Integer::sum);
        }
        TreeMap<Integer, TreeSet<Integer>> reverseMap = new TreeMap<>((l, r) -> r - l);
        for (Map.Entry<Integer, Integer> entry : frequentMap.entrySet()) {
            int frequent = entry.getValue();
            Integer num = entry.getKey();
            TreeSet<Integer> sortedNums = reverseMap.get(frequent);
            if (sortedNums == null) {
                sortedNums = new TreeSet<>();
            }
            sortedNums.add(num);
            reverseMap.put(frequent, sortedNums);
        }
        List<Integer> resultList = new ArrayList<>();
        Integer currentFrequent = reverseMap.firstKey();
        TreeSet<Integer> currentNums = reverseMap.get(currentFrequent);
        while (k != 0 && currentFrequent != null) {
            if (currentNums.isEmpty()) {
                currentFrequent = reverseMap.higherKey(currentFrequent);
                currentNums = reverseMap.get(currentFrequent);
            } else {
                Integer num = currentNums.first();
                currentNums.remove(num);
                resultList.add(num);
                k--;
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
