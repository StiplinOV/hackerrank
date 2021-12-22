package com.stiplin.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(k);
        Deque<Integer> indexDeque = new ArrayDeque<>(k);
        for (int i = 0; i < k; i++) {
            int current = nums[i];
            while (!deque.isEmpty() && deque.peekLast() < current) {
                deque.removeLast();
                indexDeque.removeLast();
            }
            deque.addLast(current);
            indexDeque.addLast(i);
        }
        for (int i = k; i < nums.length; i++) {
            while (indexDeque.peekFirst() < i - k) {
                indexDeque.removeFirst();
                deque.removeFirst();
            }
            result[i - k] = deque.peekFirst();
            int current = nums[i];
            while (!deque.isEmpty() && deque.peekLast() < current) {
                deque.removeLast();
                indexDeque.removeLast();
            }
            deque.addLast(current);
            indexDeque.addLast(i);
        }
        while (indexDeque.peekFirst() < nums.length - k) {
            indexDeque.removeFirst();
            deque.removeFirst();
        }
        result[nums.length - k] = deque.peekFirst();
        return result;
    }


}
