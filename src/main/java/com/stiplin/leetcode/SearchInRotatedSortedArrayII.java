package com.stiplin.leetcode;

public class SearchInRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

}
