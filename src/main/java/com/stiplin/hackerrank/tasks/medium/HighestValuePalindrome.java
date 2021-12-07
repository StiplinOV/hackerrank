package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.NavigableSet;
import java.util.TreeSet;

public class HighestValuePalindrome {

    /*
     * Complete the 'highestValuePalindrome' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER n
     *  3. INTEGER k
     */

    public static String highestValuePalindrome(String s, int n, int k) {
        StringBuilder stringBuilder = new StringBuilder(s);
        NavigableSet<Integer> updatedIndexes = new TreeSet<>();
        int remainingNumberOfChanges = k;
        int length = stringBuilder.length();
        for (int leftIndex = 0; leftIndex < length / 2; leftIndex++) {
            int rightIndex = length - 1 - leftIndex;
            char leftSymbol = stringBuilder.charAt(leftIndex);
            char rightSymbol = stringBuilder.charAt(rightIndex);
            if (leftSymbol > rightSymbol) {
                stringBuilder.setCharAt(rightIndex, leftSymbol);
                updatedIndexes.add(rightIndex);
                remainingNumberOfChanges--;
            } else if (leftSymbol < rightSymbol) {
                stringBuilder.setCharAt(leftIndex, rightSymbol);
                updatedIndexes.add(leftIndex);
                remainingNumberOfChanges--;
            }
        }
        if (remainingNumberOfChanges < 0) {
            return "-1";
        }
        for (int leftIndex = 0; leftIndex < length / 2; leftIndex++) {
            if (remainingNumberOfChanges == 0) {
                break;
            }
            int rightIndex = length - 1 - leftIndex;
            char symbol = stringBuilder.charAt(leftIndex);
            boolean hadLeftIndex = updatedIndexes.remove(leftIndex);
            boolean hadRightIndex = updatedIndexes.remove(rightIndex);
            if (symbol == '9') {
                continue;
            }
            if (hadLeftIndex || hadRightIndex) {
                remainingNumberOfChanges--;
                stringBuilder.setCharAt(leftIndex, '9');
                stringBuilder.setCharAt(rightIndex, '9');
            } else {
                if (remainingNumberOfChanges > 1) {
                    remainingNumberOfChanges -= 2;
                    stringBuilder.setCharAt(leftIndex, '9');
                    stringBuilder.setCharAt(rightIndex, '9');
                }
            }
        }
        if (remainingNumberOfChanges >= 1) {
            if (length % 2 == 1) {
                stringBuilder.setCharAt(length / 2, '9');
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String s = bufferedReader.readLine();

        String result = HighestValuePalindrome.highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
