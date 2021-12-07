package com.stiplin.hackerrank.tasks.medium;

import java.io.*;

public class CommonChild {

    /*
     * Complete the 'commonChild' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    public static int commonChild(String firstParam, String secondParam) {
        int[][] cache = new int[firstParam.length() + 1][secondParam.length() + 1];
        for (int i = 0; i <= firstParam.length(); i++) {
            for (int j = 0; j <= secondParam.length(); j++) {
                if (i != 0 && j != 0) {
                    char firstChar = firstParam.charAt(firstParam.length() - i);
                    char secondChar = secondParam.charAt(secondParam.length() - j);
                    if (firstChar == secondChar) {
                        cache[i][j] = cache[i - 1][j - 1] + 1;
                    } else {
                        cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
                    }
                }
            }
        }

        return cache[firstParam.length()][secondParam.length()];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = bufferedReader.readLine();

        String s2 = bufferedReader.readLine();

        int result = CommonChild.commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
