package com.stiplin.leetcode;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int maxDiff = Integer.MIN_VALUE;
        for (int cur : prices) {
            if (cur < min) {
                min = cur;
            }
            int curDiff = cur - min;
            if (curDiff > maxDiff) {
                maxDiff = curDiff;
            }
        }
        return maxDiff;
    }
}
