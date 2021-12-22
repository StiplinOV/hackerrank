package com.stiplin.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        if (s.length() == 0) {
            return 0;
        }

        SlidingWindow slidingWindow = new SlidingWindow();
        int result = 0;
        int rightPointer;
        for (rightPointer = 0; rightPointer < s.length(); rightPointer++) {
            slidingWindow.addSymbol(s.charAt(rightPointer));
            int maxRepl = slidingWindow.getMaxCharacterReplacement(k);
            if (maxRepl > result) {
                result = maxRepl;
            }
            if (maxRepl == 0) {
                break;
            }
        }
        if (rightPointer == s.length()) {
            return result;
        }

        int leftPointer = 0;
        while (rightPointer < s.length()) {
            while (slidingWindow.getMaxCharacterReplacement(k) == 0) {
                slidingWindow.removeSymbol(s.charAt(leftPointer));
                leftPointer++;
            }

            int mcr = slidingWindow.getMaxCharacterReplacement(k);
            if (mcr > result) {
                result = mcr;
            }
            rightPointer++;

            while (rightPointer < s.length() && mcr != 0) {
                slidingWindow.addSymbol(s.charAt(rightPointer));
                mcr = slidingWindow.getMaxCharacterReplacement(k);
                if (mcr > result) {
                    result = mcr;
                }
                if (mcr != 0) {
                    rightPointer++;
                }
            }
        }

        return result;
    }

    static class SlidingWindow {

        final Map<Character, Integer> frequentMap = new HashMap<>();
        int cachedSum = 0;

        int getMaxCharacterReplacement(int maxNumberOfReplacement) {
            int result = 0;

            for (int value : frequentMap.values()) {
                int candidate = cachedSum - value;
                if (candidate <= maxNumberOfReplacement && cachedSum > result) {
                    result = cachedSum;
                    break;
                }
            }

            return result;
        }

        void addSymbol(char symbol) {
            frequentMap.merge(symbol, 1, Integer::sum);
            cachedSum++;
        }

        void removeSymbol(char symbol) {
            frequentMap.merge(symbol, -1, Integer::sum);
            cachedSum--;
        }

    }

}
