package com.stiplin.leetcode;

public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int minIndex = searchMinIndex(nums);
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right)/2;
            int midIndex = mid + minIndex >= nums.length ? (mid + minIndex - nums.length) : mid + minIndex;
            int midVal = nums[midIndex];
            if (midVal == target) {
                return midIndex;
            }
            if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }

    private int searchMinIndex(int[] nums) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right)/2;
            int midVal = nums[mid];
            int leftVal = nums[left];
            int rightVal = nums[right - 1];
            if (midVal == leftVal || midVal == rightVal) {
                if (leftVal < rightVal) {
                    return left;
                } else {
                    return right - 1;
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
