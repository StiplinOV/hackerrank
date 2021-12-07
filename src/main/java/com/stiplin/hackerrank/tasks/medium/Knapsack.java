package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Knapsack {

    /*
     * Complete the 'unboundedKnapsack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */


    public static int unboundedKnapsack(int targetSum, List<Integer> arr) {
        return unboundedKnapsack(targetSum, new TreeSet<>(arr));
    }

    public static int unboundedKnapsack(int targetSum, NavigableSet<Integer> arr) {
        int maxResult = 0;
        for (int elem : arr) {
            int remainder = targetSum % elem;
            if (remainder == 0) {
                return targetSum;
            }
            if (targetSum > elem) {
                int local = elem + unboundedKnapsack(targetSum - elem, arr);
                if (local == targetSum) {
                    return targetSum;
                }
                if (local > maxResult) {
                    maxResult = local;
                }
            }
        }
        // Write your code here
        return maxResult;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());


        for (int i = 0; i < t; i++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int k = Integer.parseInt(firstMultipleInput[1]);
            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = Knapsack.unboundedKnapsack(k, arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }


        bufferedReader.close();
        bufferedWriter.close();
    }

}
