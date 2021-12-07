package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Equal {

    /*
     * Complete the 'equal' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    public static int equal(List<Integer> arrParam) {
        List<Integer> arr = new ArrayList<>(arrParam);
        Collections.sort(arr);
        int min = arr.get(0);

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int localResult = 0;
            for (int j = 0; j < arr.size(); j++) {
                localResult += getNumberOfOperations(arr.get(j) + i, min);
            }
            result = Math.min(result, localResult);
        }

        return result;
    }

    private static int getNumberOfOperations(int value, int min) {
        int result = 0;
        int remainder = value - min;
        if (remainder >= 5) {
            result += remainder / 5;
            remainder = remainder % 5;
        }
        if (remainder >= 2) {
            result += remainder / 2;
            remainder = remainder % 2;
        }
        return result + remainder;
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

                int result = Equal.equal(arr);

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
