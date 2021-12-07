package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SherlockAndCost {

    /*
     * Complete the 'cost' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY B as parameter.
     */

    public static int cost(List<Integer> array) {
        if (array.size() == 1) {
            return 0;
        }
        int li = Math.abs(array.get(0) - 1);
        int hi = Math.abs(array.get(1) - 1);

        for (int i = 2; i < array.size(); i++) {
            int cur = array.get(i);
            int prev = array.get(i - 1);
            int liNext = Math.max(li, hi + Math.abs(prev - 1));
            int hiNext = Math.max(li + Math.abs(cur - 1), hi + Math.abs(prev - cur));
            li = liNext;
            hi = hiNext;

        }
        return Math.max(li, hi);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> B = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                int result = SherlockAndCost.cost(B);

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
