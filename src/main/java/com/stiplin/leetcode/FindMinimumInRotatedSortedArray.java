package com.stiplin.leetcode;

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right)/2;
            int midVal = nums[mid];
            int leftVal = nums[left];
            int rightVal = nums[right - 1];
            if (midVal == leftVal || midVal == rightVal) {
                if (leftVal < rightVal) {
                    return nums[left];
                } else {
                    return nums[right - 1];
                }
            }
            if (midVal > rightVal) {
                left = mid + 1;
            } else {
                right = mid + 1;
            }
        }

        return -1;
    }
}
