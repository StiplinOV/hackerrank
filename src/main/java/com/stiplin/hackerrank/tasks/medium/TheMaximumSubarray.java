package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class TheMaximumSubarray {

    /*
     * Complete the 'maxSubarray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    public static List<Long> maxSubarray(List<Integer> arr) {
        long secondResult = 0;
        long maxNegative = Long.MIN_VALUE;
        boolean allNegative = true;
        for (int elem : arr) {
            if (elem >= 0) {
                allNegative = false;
                secondResult += elem;
            } else if (elem > maxNegative) {
                maxNegative = elem;
            }
        }
        if (allNegative) {
            return Arrays.asList(maxNegative, maxNegative);
        }
        long sum = 0;
        long maxSum = 0;
        for (Integer integer : arr) {
            sum = Math.max(0, sum + integer);
            maxSum = Math.max(maxSum, sum);
        }

        return Arrays.asList(maxSum, secondResult);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Long> result = TheMaximumSubarray.maxSubarray(arr);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

}
