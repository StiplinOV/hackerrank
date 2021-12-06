package com.stiplin.hackerrank.tasks.hard;

import java.io.*;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MaximumSubarraySum {

    /*
     * Complete the 'maximumSum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER_ARRAY a
     *  2. LONG_INTEGER m
     */

    public static long maximumSum(List<Long> a, long m) {
        long maxSum = 0;

        TreeSet<Long> prefix = new TreeSet<>();
        long currSum = 0;

        for (int i = 0; i < a.size(); i++) {
            currSum = (currSum + a.get(i) % m) % m;
            maxSum = Math.max(maxSum, currSum);

            Long pr = prefix.higher(currSum);
            if (pr != null) {
                maxSum = Math.max(maxSum, (currSum - pr + m) % m);
            }
            prefix.add(currSum);
        }

        return maxSum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                long m = Long.parseLong(firstMultipleInput[1]);

                List<Long> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Long::parseLong)
                        .collect(toList());

                long result = MaximumSubarraySum.maximumSum(a, m);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

}

