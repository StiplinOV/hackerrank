package com.stiplin.leetcode;

public class GuessNumberHigherOrLower {

    int guess(int num) {//1702766719
        System.out.println(num);     //2126753390
        return Integer.compare(num, 6);
    }

    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (guess(mid) == 1) {
                left = mid + 1;
            } else if (guess(mid) == -1) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
