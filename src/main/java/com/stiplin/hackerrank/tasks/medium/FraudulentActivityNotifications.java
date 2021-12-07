package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FraudulentActivityNotifications {

    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int result = 0;
        if (expenditure.size() <= d) {
            return result;
        }
        NavigableMap<Integer, Integer> countMap = new TreeMap<>();
        for (int i = 0; i < d; i++) {
            increment(countMap, expenditure.get(i));
        }
        for (int i = d; i < expenditure.size(); i++) {
            int current = expenditure.get(i);
            int prev = expenditure.get(i - d);
            //System.out.println("current " + current + " countMap " + countMap + " " + greaterOrEqualThanMedian(countMap, current, d));
            if (greaterOrEqualThanMedian(countMap, current, d)) {
                result++;
            }
            decrement(countMap, prev);
            increment(countMap, current);
        }
        return result;
    }

    private static void increment(NavigableMap<Integer, Integer> countMap, int element) {
        countMap.merge(element, 1, Integer::sum);
    }

    private static void decrement(NavigableMap<Integer, Integer> countMap, int element) {
        countMap.merge(element, -1, Integer::sum);
    }

    private static boolean greaterOrEqualThanMedian(NavigableMap<Integer, Integer> countMap, int element, int size) {
        int middleElementIndex = size / 2;
        if (size % 2 == 0) {
            int leftElement = getElementByIndex(countMap, middleElementIndex - 1);
            int rightElement = getElementByIndex(countMap, middleElementIndex);
            return element >= leftElement + rightElement;
        } else {
            return element >= getElementByIndex(countMap, middleElementIndex) * 2;
        }
    }

    private static int getElementByIndex(NavigableMap<Integer, Integer> countMap, int index) {
        int leftIndex = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int element = entry.getKey();
            int count = entry.getValue();
            int rightIndexExclusive = leftIndex + count;
            if (index >= leftIndex && index < rightIndexExclusive) {
                return element;
            }
            leftIndex = rightIndexExclusive;
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = FraudulentActivityNotifications.activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
