package com.stiplin.leetcode;

public class BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1) {
            return 0;
        }
        int result = 0;
        boolean bought = false;
        int openedPrice = 0;

        for(int i = 1; i < prices.length; i++) {
            int cur = prices[i - 1];
            int next = prices[i];
            if (bought) {
                if (next < cur) {
                    bought = false;
                    result += (cur - openedPrice);
                    openedPrice = 0;
                }
            } else {
                if (next > cur) {
                    bought = true;
                    openedPrice = cur;
                }
            }
        }
        if (bought) {
            result += prices[prices.length - 1] - openedPrice;
        }
        return result;
    }
}
