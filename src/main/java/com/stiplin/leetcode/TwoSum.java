package com.stiplin.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numsCountMap = new HashMap<>();
        int[] result = new int[]{};
        for (int num : nums) {
            numsCountMap.merge(num, 1, Integer::sum);
        }
        for (Integer num : numsCountMap.keySet()) {
            int remaining = target - num;
            if (numsCountMap.containsKey(remaining)) {
                if (remaining == num) {
                    if (numsCountMap.get(num) > 1) {
                        result = new int[]{num, num};
                        break;
                    }
                } else {
                    result = new int[]{num, remaining};
                    break;
                }
            }
        }
        boolean same = result[0] == result[1];
        for (int i = 0; i < nums.length; i++) {
            if (result[0] == nums[i]) {
                result[0] = i;
                break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (result[1] == nums[i]) {
                if (same) {
                    same = false;
                } else {
                    result[1] = i;
                    break;
                }
            }
        }
        return result;
    }
}
