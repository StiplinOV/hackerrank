package com.stiplin.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        TreeMap<Integer, Integer> inMap = new TreeMap<>();
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            if (inMap.containsKey(left)) {
                inMap.put(left, Math.max(right, inMap.get(left)));
            } else {
                inMap.put(left, right);
            }
        }
        TreeMap<Integer, Integer> outMap = new TreeMap<>();
        Integer curLeft = inMap.firstKey();
        int curRight = inMap.get(curLeft);

        Integer nextLeft = inMap.higherKey(curLeft);
        while (nextLeft != null) {
            Integer nextRight = inMap.get(nextLeft);
            if (curRight >= nextLeft) {
                curRight = Math.max(curRight, nextRight);
                inMap.remove(nextLeft);
                nextLeft = inMap.higherKey(curLeft);
                continue;
            } else {
                outMap.put(curLeft, curRight);
            }
            curRight = nextRight;
            curLeft = nextLeft;
            nextLeft = inMap.higherKey(curLeft);
        }
        outMap.put(curLeft, curRight);
        int[][] result = new int[outMap.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : outMap.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }
        return result;
    }

}
