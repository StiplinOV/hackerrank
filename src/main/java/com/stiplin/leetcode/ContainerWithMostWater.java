package com.stiplin.leetcode;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int result = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;
        int min = 0;
        while (leftPointer < rightPointer) {
            int leftHeight = height[leftPointer];
            int rightHeight = height[rightPointer];
            while (leftHeight <= min && leftPointer < rightPointer) {
                leftPointer++;
                leftHeight = height[leftPointer];
            }
            while (rightHeight <= min && leftPointer < rightPointer) {
                rightPointer--;
                rightHeight = height[rightPointer];
            }
            min = Math.min(leftHeight, rightHeight);
            int area = min * (rightPointer - leftPointer);
            if (area > result) {
                result = area;
            }
        }
        return result;
    }
}
