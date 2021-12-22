package com.stiplin.hackerrank.tasks.easy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TwoDArrayDS {

    /*
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int hourglassSum(List<List<Integer>> arr) {
        // Write your code here
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int hourglassSum = calculateHourglassSumm(i, j, arr);
                if (hourglassSum > maxSum) {
                    maxSum = hourglassSum;
                }
            }
        }
        return maxSum;
    }

    private static int calculateHourglassSumm(int i, int j, List<List<Integer>> arr) {
        int result = 0;
        result += arr.get(i).get(j);
        result += arr.get(i).get(j + 1);
        result += arr.get(i).get(j + 2);
        result += arr.get(i + 1).get(j + 1);
        result += arr.get(i + 2).get(j);
        result += arr.get(i + 2).get(j + 1);
        result += arr.get(i + 2).get(j + 2);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = TwoDArrayDS.hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
