package com.stiplin.hackerrank.tasks.medium;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FormingAMagicSquare {

    private static List<Integer[][]> allPossibleSquares = new ArrayList<>();

    static {
        allPossibleSquares.add(new Integer[][]{
                {6, 7, 2},
                {1, 5, 9},
                {8, 3, 4},
        });
        allPossibleSquares.add(new Integer[][]{
                {6, 1, 8},
                {7, 5, 3},
                {2, 9, 4},
        });
        allPossibleSquares.add(new Integer[][]{
                {8, 3, 4},
                {1, 5, 9},
                {6, 7, 2},
        });
        allPossibleSquares.add(new Integer[][]{
                {8, 1, 6},
                {3, 5, 7},
                {4, 9, 2},
        });
        allPossibleSquares.add(new Integer[][]{
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 8},
        });
        allPossibleSquares.add(new Integer[][]{
                {4, 3, 8},
                {9, 5, 1},
                {2, 7, 6},
        });
        allPossibleSquares.add(new Integer[][]{
                {2, 9, 4},
                {7, 5, 3},
                {6, 1, 8},
        });
        allPossibleSquares.add(new Integer[][]{
                {4, 9, 2},
                {3, 5, 7},
                {8, 1, 6},
        });
    }

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int result = 10000000;

        for (Integer[][] square : allPossibleSquares) {
            int cost = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cost += Math.abs(square[i][j] - s[i][j]);
                }
            }
            if (cost < result) {
                result = cost;
            }
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
