package com.stiplin.hackerrank.tasks.medium;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClimbingTheLeaderboard {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        List<Integer> scoreList = new ArrayList<>();

        for (int i = 0; i < scores.length; i++) {
            tryAddScore(scoreList, scores[i]);
        }

        int[] result = new int[alice.length];
        for (int i = 0; i < alice.length; i++) {
            result[i] = tryAddScore(scoreList, alice[i]);
        }

        return result;
    }

    static int tryAddScore(List<Integer> scoreList, int score) {
        int scoreListSize = scoreList.size();

        if (scoreListSize == 0) {
            scoreList.add(score);
            return 1;
        } else {
            int low = 0;
            int high = scoreListSize - 1;
            int mid = 0;

            while (low <= high) {
                mid = (low + high) / 2;
                Integer tempScore = scoreList.get(mid);
                if (tempScore < score) {
                    high = mid - 1;
                } else if (tempScore > score) {
                    low = mid + 1;
                } else {
                    return mid + 1;
                }
            }
            if (scoreList.get(mid) > score) {
                mid++;
                scoreList.add(mid, score);
            } else {
                scoreList.add(mid, score);
            }

            return mid + 1;
        }
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
