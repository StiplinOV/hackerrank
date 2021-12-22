package com.stiplin.leetcode;

public class BestTimeToBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        if(prices.length == 1) {
            return 0;
        }
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        int[] waitAfterBuy = new int[prices.length];
        int[] waitAfterNothing = new int[prices.length];

        buy[0] = - prices[0] - fee;
        sell[0] = -500000;
        waitAfterBuy[0] = -500000;
        waitAfterNothing[0] = 0;

        for (int i = 1; i < prices.length; i++) {
            buy[i] = Math.max(sell[i - 1], waitAfterNothing[i - 1]) - prices[i] - fee;
            sell[i] = Math.max(buy[i - 1], waitAfterBuy[i - 1]) + prices[i];
            waitAfterBuy[i] = Math.max(waitAfterBuy[i - 1], buy[i - 1]);
            waitAfterNothing[i] = Math.max(waitAfterNothing[i - 1], sell[i  - 1]);
        }

        return Math.max(Math.max(buy[prices.length - 1], sell[prices.length - 1]), Math.max(waitAfterBuy[prices.length - 1], waitAfterNothing[prices.length - 1]));
    }

}
