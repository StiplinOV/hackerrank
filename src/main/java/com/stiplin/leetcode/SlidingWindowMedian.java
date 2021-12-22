package com.stiplin.leetcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k) {
        int resultSize = nums.length - k + 1;
        double[] result = new double[resultSize];

        if (k == 1) {
            for(int i = 0; i < resultSize; i++) {
                result[i] = nums[i];
            }
            return result;
        }

        if (k % 2 == 0) {
            int queueSize = k / 2;
            PriorityQueue<Long> leftQueue = new PriorityQueue<>(queueSize, (a, b) -> {
                if (b > a) {
                    return 1;
                } else if (a > b) {
                    return -1;
                }
                return 0;
            });
            PriorityQueue<Long> rightQueue = new PriorityQueue<>(queueSize, (a, b) -> {
                if (b < a) {
                    return 1;
                } else if (a < b) {
                    return -1;
                }
                return 0;
            });
            long[] slideWindow = new long[k];
            for (int i = 0; i < k; i++) {
                slideWindow[i] = nums[i];
            }
            Arrays.sort(slideWindow);
            for (int i = 0; i < queueSize; i++) {
                leftQueue.add(slideWindow[i]);
            }
            for (int i = 0; i < queueSize; i++) {
                rightQueue.add(slideWindow[k - i - 1]);
            }
            long left = leftQueue.peek();
            long right = rightQueue.peek();
            result[0] = ((double) left + (double) right) / 2;
            for (int i = 0; i + k < nums.length; i++) {
                long removed = nums[i];
                long added = nums[k + i];

                if (removed <= left) {
                    leftQueue.remove(removed);
                } else {
                    rightQueue.remove(removed);
                }
                if (added <= left) {
                    leftQueue.add(added);
                } else {
                    rightQueue.add(added);
                }
                while (leftQueue.size() > rightQueue.size()) {
                    rightQueue.add(leftQueue.remove());
                }
                while (rightQueue.size() > leftQueue.size()) {
                    leftQueue.add(rightQueue.remove());
                }

                left = leftQueue.peek();
                right = rightQueue.peek();
                if (left == right) {
                    result[i + 1] = left;
                } else {
                    result[i + 1] = ((double) left + (double) right) / 2;
                }
            }
        } else {
            int leftQueueSize = k / 2 + 1;
            int rightQueueSize = k / 2;
            PriorityQueue<Long> leftQueue = new PriorityQueue<>(leftQueueSize, (a, b) -> {
                if (b > a) {
                    return 1;
                } else if (a > b) {
                    return -1;
                }
                return 0;
            });
            PriorityQueue<Long> rightQueue = new PriorityQueue<>(rightQueueSize, (a, b) -> {
                if (b < a) {
                    return 1;
                } else if (a < b) {
                    return -1;
                }
                return 0;
            });
            long[] slideWindow = new long[k];
            for (int i = 0; i < k; i++) {
                slideWindow[i] = nums[i];
            }
            Arrays.sort(slideWindow);
            for (int i = 0; i < leftQueueSize; i++) {
                leftQueue.add(slideWindow[i]);
            }
            for (int i = 0; i < rightQueueSize; i++) {
                rightQueue.add(slideWindow[k - i - 1]);
            }
            result[0] = leftQueue.peek();
            for (int i = 0; i + k < nums.length; i++) {
                long removed = nums[i];
                long added = nums[k + i];

                if (leftQueue.contains(removed)) {
                    leftQueue.remove(removed);
                } else {
                    rightQueue.remove(removed);
                }

                if(leftQueue.peek() > added) {
                    leftQueue.add(added);
                } else {
                    rightQueue.add(added);
                }

                while (leftQueue.size() < leftQueueSize) {
                    leftQueue.add(rightQueue.remove());
                }
                while (rightQueue.size() < rightQueueSize) {
                    rightQueue.add(leftQueue.remove());
                }

                result[i + 1] = leftQueue.peek();
            }
        }

        return result;
    }

}
