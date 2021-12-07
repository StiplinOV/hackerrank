package com.stiplin.hackerrank.tasks.medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AlmostSorted {

    /*
     * Complete the 'almostSorted' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void almostSorted(List<Integer> arr) {
        int prev = arr.get(0);
        Integer firstStrangeIndex = null;
        Integer secondStrangeIndex = null;
        Integer thirdStrangeIndex = null;
        for (int i = 1; i < arr.size(); i++) {
            int current = arr.get(i);
            if (prev > current) {
                if (secondStrangeIndex == null) {
                    if (firstStrangeIndex == null) {
                        firstStrangeIndex = i;
                    } else {
                        secondStrangeIndex = i;
                    }
                }
            } else if (secondStrangeIndex != null && thirdStrangeIndex == null) {
                thirdStrangeIndex = i;
            }
            prev = current;
        }
        if (firstStrangeIndex == null) {
            System.out.println("yes");
            return;
        } else if (secondStrangeIndex == null) {
            List<Integer> swappedArr = swap(arr, firstStrangeIndex - 1, firstStrangeIndex);
            if (isSorted(swappedArr)) {
                System.out.println("yes");
                System.out.println("swap " + firstStrangeIndex + " " + (firstStrangeIndex + 1));
                return;
            }
        } else if (thirdStrangeIndex == null) {
            List<Integer> swappedArr = swap(arr, firstStrangeIndex - 1, secondStrangeIndex);
            if (isSorted(swappedArr)) {
                System.out.println("yes");
                System.out.println("swap " + firstStrangeIndex + " " + (secondStrangeIndex + 1));
                return;
            } else {
                List<Integer> reversedArr = reverse(arr, firstStrangeIndex - 1, arr.size());
                if (isSorted(reversedArr)) {
                    System.out.println("yes");
                    System.out.println("reverse " + firstStrangeIndex + " " + (secondStrangeIndex + 1));
                    return;
                }
            }
        } else {
            List<Integer> swappedArr = swap(arr, firstStrangeIndex - 1, secondStrangeIndex);
            if (isSorted(swappedArr)) {
                System.out.println("yes");
                System.out.println("swap " + firstStrangeIndex + " " + (secondStrangeIndex + 1));
                return;
            } else {
                List<Integer> reversedArr = reverse(arr, firstStrangeIndex - 1, thirdStrangeIndex);
                if (isSorted(reversedArr)) {
                    System.out.println("yes");
                    System.out.println("reverse " + firstStrangeIndex + " " + thirdStrangeIndex);
                    return;
                }
            }
        }
        System.out.println("no");
    }

    private static List<Integer> swap(List<Integer> src, int left, int right) {
        List<Integer> result = new ArrayList<Integer>(src);
        result.set(left, src.get(right));
        result.set(right, src.get(left));
        return result;
    }

    private static List<Integer> reverse(List<Integer> src, int left, int rightExclusive) {
        List<Integer> result = new ArrayList<Integer>(src.size());
        boolean startReverse = false;
        for (int i = 0; i < src.size(); i++) {
            if (i == left) {
                startReverse = true;
            }
            if (i == rightExclusive) {
                startReverse = false;
            }
            if (startReverse) {
                result.add(src.get(rightExclusive - 1 - (i - left)));
            } else {
                result.add(src.get(i));
            }
        }
        return result;
    }

    private static boolean isSorted(List<Integer> arr) {
        int prev = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            int current = arr.get(i);
            if (prev > current) {
                return false;
            }
            prev = current;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        AlmostSorted.almostSorted(arr);

        bufferedReader.close();
    }

}
