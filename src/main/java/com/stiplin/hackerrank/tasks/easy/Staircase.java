package com.stiplin.hackerrank.tasks.easy;

import java.util.Scanner;

public class Staircase {

    // Complete the staircase function below.
    static void staircase(int n) {
        for (int i = 0; i < n; i++) {
            final int spaceCount = n - i - 1;
            for (int j = 0; j < spaceCount; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < n - spaceCount; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        staircase(n);

        scanner.close();
    }
}
