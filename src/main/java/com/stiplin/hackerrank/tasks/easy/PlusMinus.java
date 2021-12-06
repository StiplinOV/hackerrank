package com.stiplin.hackerrank.tasks.easy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class PlusMinus {

    // Complete the plusMinus function below.
    static void plusMinus(int[] arr) {
        int positiveCount = 0;
        int negativeCount = 0;
        int zeroCount = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                positiveCount++;
            } else if (arr[i] < 0) {
                negativeCount++;
            } else {
                zeroCount++;
            }
        }

        BigDecimal count = new BigDecimal(arr.length);

        System.out.println(new BigDecimal(positiveCount).divide(count, 6, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal(negativeCount).divide(count, 6, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal(zeroCount).divide(count, 6, RoundingMode.HALF_UP));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        plusMinus(arr);

        scanner.close();
    }
}
