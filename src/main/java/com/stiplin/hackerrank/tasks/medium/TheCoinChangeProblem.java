package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TheCoinChangeProblem {

    /*
     * Complete the 'getWays' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. LONG_INTEGER_ARRAY c
     */


    public static long getWays(long n, List<Long> c) {
        return getWays(n, new TreeSet<>(c));
    }

    static Map<String, Long> cache = new HashMap<>();

    public static long getWays(long n, NavigableSet<Long> c) {
        if (cache.containsKey(c.toString() + ":" + n)) {
            return cache.get(c.toString() + ":" + n);
        }
        if (c.first() > n) {
            cache.put(c.toString() + ":" + n, 0L);
            return 0;
        }
        if (c.size() == 1) {
            cache.put(c.toString() + ":" + n, n % c.first() == 0 ? 1L : 0L);
            return n % c.first() == 0 ? 1 : 0;
        }

        Long value = c.first();
        long result = 0;
        for (int j = 0; j <= n / value; j++) {
            if (n - (value * j) == 0) {
                result++;
            } else {
                result += getWays(n - (value * j), c.tailSet(value, false));
            }
        }
        cache.put(c.toString() + ":" + n, result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Long> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = TheCoinChangeProblem.getWays(n, c);

        bufferedWriter.write(String.valueOf(ways));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
