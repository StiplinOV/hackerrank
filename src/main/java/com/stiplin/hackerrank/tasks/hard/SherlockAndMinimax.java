package com.stiplin.hackerrank.tasks.hard;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SherlockAndMinimax {

    /*
     * Complete the 'sherlockAndMinimax' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER p
     *  3. INTEGER q
     */

    public static int sherlockAndMinimax(List<Integer> arr, int p, int q) {
        // Write your code here
        NavigableSet<Integer> sortedArr = new TreeSet<>(arr);
        Integer left = sortedArr.floor(p);
        Integer right = sortedArr.ceiling(q);
        if (left == null) {
            left = sortedArr.ceiling(p);
        }
        if (right == null) {
            right = sortedArr.floor(q);
        }
        NavigableSet<Integer> subset = sortedArr.subSet(left, true, right, true);

        if (subset.size() == 1) {
            int element = subset.first();
            if (element >= p && element <= q) {
                if (element - p >= q - element) {
                    return p;
                } else {
                    return q;
                }
            } else {
                if (element < p) {
                    return q;
                } else {
                    return p;
                }
            }
        }
        int maxDiff = Integer.MIN_VALUE;
        int betterPosition = p;
        Integer lower = subset.floor(p);
        int next = subset.higher(p);
        if (lower != null) {
            if (p - lower < next - p) {
                int middlePoint = (next + lower) / 2;
                if (q > middlePoint) {
                    maxDiff = (next - lower) / 2;
                    betterPosition = middlePoint;
                } else {
                    return q;
                }
            } else {
                maxDiff = next - p;
            }
        } else {
            maxDiff = next - p;
        }

        int currentElement = subset.higher(betterPosition);
        Integer nextElement = subset.higher(currentElement);
        while (nextElement != null) {
            int middlePoint = (currentElement + nextElement) / 2;
            int diff = Math.min(Math.abs(currentElement - q), Math.abs(nextElement - q));
            if (q >= middlePoint) {
                diff = (nextElement - currentElement) / 2;
                if (diff > maxDiff) {
                    maxDiff = diff;
                    betterPosition = middlePoint;
                }
            } else {
                if (diff > maxDiff) {
                    return q;
                }
                return betterPosition;
            }

            currentElement = nextElement;
            nextElement = subset.higher(currentElement);
        }
        if (q > currentElement) {
            if (q - currentElement > maxDiff) {
                betterPosition = q;
            }
        }
        return betterPosition;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int p = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        int result = SherlockAndMinimax.sherlockAndMinimax(arr, p, q);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
