package com.stiplin.hackerrank.tasks.easy;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MinimumAbsoluteDifferenceInAnArray {

    /*
     * Complete the 'minimumAbsoluteDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int minimumAbsoluteDifference(List<Integer> arr) {
        // Write your code here
        int result = Integer.MAX_VALUE;
        Collections.sort(arr);
        int prev = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            int cur = arr.get(i);
            int diff = diff(cur, prev);
            if (result > diff) {
                result = diff;
            }
            prev = cur;
        }
        return result;
    }

    private static int diff(int a, int b) {
        if (a > b) {
            return a - b;
        }
        return b - a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = MinimumAbsoluteDifferenceInAnArray.minimumAbsoluteDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
