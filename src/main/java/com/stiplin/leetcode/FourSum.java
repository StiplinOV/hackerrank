package com.stiplin.leetcode;

import java.util.*;

public class FourSum {

    public List<List<Integer>> fourSum(int[] numsParam, int target) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : numsParam) {
            countMap.merge(num, 1, Integer::sum);
        }
        int numsLength = 0;
        for (int count: countMap.values()) {
            numsLength += Math.min(count, 4);
        }
        int[] nums = new int[numsLength];
        int numsPointer = 0;
        for (Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
            int number = entry.getKey();
            int count = Math.min(entry.getValue(), 4);
            for (int i = 0; i < count; i++) {
                nums[numsPointer++] = number;
            }
        }
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == target) {
                            List<Integer> quadruplet = new ArrayList<>();
                            quadruplet.add(nums[i]);
                            quadruplet.add(nums[j]);
                            quadruplet.add(nums[k]);
                            quadruplet.add(nums[l]);
                            Collections.sort(quadruplet);
                            result.add(quadruplet);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }
}
