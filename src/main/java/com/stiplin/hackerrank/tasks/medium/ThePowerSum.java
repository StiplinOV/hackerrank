package com.stiplin.hackerrank.tasks.medium;

import java.io.*;

public class ThePowerSum {

    /*
     * Complete the 'powerSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER X
     *  2. INTEGER N
     */

    public static int powerSum(int X, int N) {
        return powerSum(X, N, 1);
    }

    public static int powerSum(int X, int N, int base) {
        if (X == 0) {
            return 0;
        }
        int pow = (int) Math.pow(base, N);
        if (X == pow) {
            return 1;
        }
        if (pow > X) {
            return 0;
        }
        return powerSum(X, N, base + 1) + powerSum(X - pow, N, base + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int X = Integer.parseInt(bufferedReader.readLine().trim());

        int N = Integer.parseInt(bufferedReader.readLine().trim());

        int result = ThePowerSum.powerSum(X, N);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
