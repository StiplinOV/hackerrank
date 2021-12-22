package com.stiplin.leetcode;

public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int height = matrix.length;
        if (height == 0) {
            return false;
        }
        int width = matrix[0].length;
        if (width == 0) {
            return false;
        }

        int left = 0;
        int right = height * width;
        while (left < right) {
            int mid = (left + right) / 2;
            int row = row(mid, height, width);
            int col = col(mid, width);
            int val = matrix[row][col];
            if (val == target) {
                return true;
            } else if (val > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    private int row(int num, int height, int width) {
        return num / width;
    }

    private int col(int num, int width) {
        return num % width;
    }

}
