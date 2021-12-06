package com.stiplin.hackerrank.tasks.easy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BreakingTheRecords {

    // Complete the breakingRecords function below.
    static int[] breakingRecords(int[] scores) {
        if (scores.length == 0) {
            return new int[0];
        }
        int min = scores[0];
        int max = scores[0];

        int minCnt = 0;
        int maxCnt = 0;

        for (int i = 0; i < scores.length; i++) {
            int currentScore = scores[i];

            if (currentScore < min) {
                minCnt++;
                min = currentScore;
            }
            if (currentScore > max) {
                maxCnt++;
                max = currentScore;
            }
        }
        return new int[]{maxCnt, minCnt};
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[n];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int[] result = breakingRecords(scores);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
