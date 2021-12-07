package com.stiplin.hackerrank.tasks.medium;

import java.io.*;

public class ConstructTheArray {

    /*
     * Complete the 'countArray' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER x
     */

    public static long countArray(int n, int k, int x) {
        long endOne = 1;
        long endNotOne = 0;
        for (int i = 2; i <= n; ++i) {
            long nextEndOne = endNotOne * (k - 1);
            long nextEndNotOne = endOne + endNotOne * (k - 2);
            endOne = nextEndOne % 1000000007;
            endNotOne = nextEndNotOne % 1000000007;
        }
        return x == 1 ? endOne : endNotOne;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        int x = Integer.parseInt(firstMultipleInput[2]);

        long answer = ConstructTheArray.countArray(n, k, x);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
