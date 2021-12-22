package com.stiplin.leetcode;

public class BinarySearch {

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int from = 0;
        int to = nums.length;
        while (from < to) {
            int middle = (from + to) / 2;
            int middleVal = nums[middle];
            if (target == middleVal) {
                return middle;
            }
            if (target > middleVal) {
                from = middle + 1;
            }
            if (target < middleVal) {
                to = middle;
            }
        }
        return -1;
    }
}
