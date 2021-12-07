package com.stiplin.hackerrank.tasks.medium;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TheBombermanGame {

    // Complete the bomberMan function below.
    static String[] bomberMan(int n, String[] grid) {
        if (n == 1) {
            return grid;
        }

        char[][] charResult = new char[grid.length][grid[0].length()];

        boolean even = n % 2 == 0;
        if (even) {
            for (int i = 0; i < charResult.length; i++) {
                char[] charPart = charResult[i];
                for (int j = 0; j < charPart.length; j++) {
                    charResult[i][j] = 'O';
                }
            }
            return convertCharsToStrings(charResult);
        }

        for (int i = 0; i < grid.length; i++) {
            charResult[i] = grid[i].toCharArray();
        }


        for (int k = 0; k < ((n / 2) % 2) + 2; k++) {
            for (int i = 0; i < charResult.length; i++) {
                char[] charPart = charResult[i];
                for (int j = 0; j < charPart.length; j++) {
                    char symbol = charResult[i][j];
                    char resultSymbol;
                    if (symbol == '.') {
                        resultSymbol = 'O';
                    } else if (symbol == 'e') {
                        resultSymbol = '.';
                    } else {
                        resultSymbol = '.';
                        if (j < (charPart.length - 1) && charResult[i][j + 1] != 'O') {
                            charResult[i][j + 1] = 'e';
                        }
                        if (j > 0) {
                            charResult[i][j - 1] = '.';
                        }
                        if (i > 0) {
                            charResult[i - 1][j] = '.';
                        }
                        if (i < (charResult.length - 1) && charResult[i + 1][j] != 'O') {
                            charResult[i + 1][j] = 'e';
                        }
                    }

                    charResult[i][j] = resultSymbol;
                }
            }
        }

        return convertCharsToStrings(charResult);
    }

    private static String[] convertCharsToStrings(char[][] chars) {
        String[] result = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i] = new String(chars[i]);
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] rcn = scanner.nextLine().split(" ");

        int r = Integer.parseInt(rcn[0]);

        int c = Integer.parseInt(rcn[1]);

        int n = Integer.parseInt(rcn[2]);

        String[] grid = new String[r];

        for (int i = 0; i < r; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] result = bomberMan(n, grid);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
