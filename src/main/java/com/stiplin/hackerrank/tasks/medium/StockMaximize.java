package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StockMaximize {

    /*
     * Complete the 'stockmax' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY prices as parameter.
     */


    public static long stockmax(List<Integer> prices) {
        if (prices.size() == 1) {
            return 0;
        }

        int[] maxs = new int[prices.size()];
        int max = Integer.MIN_VALUE;
        for (int i = prices.size() - 1; i >= 0; i--) {
            int current = prices.get(i);
            if (current > max) {
                max = current;
            }
            maxs[i] = max;
        }

        // Write your code here
        int current = prices.get(0);
        long items = 0;
        long result = 0;
        if (current < maxs[0]) {
            result -= current;
            items++;
        }

        for (int i = 1; i < prices.size(); i++) {
            current = prices.get(i);
            if (current < maxs[i]) {
                result -= current;
                items++;
            }
            if (current == maxs[i]) {
                result += current * items;
                items = 0;
            }
        }
        if (items > 0) {
            result += items * prices.get(prices.size() - 1);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                long result = StockMaximize.stockmax(prices);

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
