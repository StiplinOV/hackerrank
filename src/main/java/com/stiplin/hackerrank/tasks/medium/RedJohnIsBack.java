package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class RedJohnIsBack {

    /*
     * Complete the 'redJohn' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER n as parameter.
     */

    public static int redJohn(int n) {
        return primes.headSet(ways(n), true).size();
    }

    static boolean[] eratosphen = new boolean[227271];

    private static NavigableSet<Integer> primes = new TreeSet<>();

    static {
        Arrays.fill(eratosphen, true);
        eratosphen[0] = false;
        eratosphen[1] = false;
        for (int i = 2; i < eratosphen.length; ++i) {
            if (eratosphen[i]) {
                for (int j = 2; i * j < eratosphen.length; ++j) {
                    eratosphen[i * j] = false;
                }
            }
        }
        for (int i = 0; i < eratosphen.length; i++) {
            if (eratosphen[i]) {
                primes.add(i);
            }
        }
    }

    private static final Map<Integer, Integer> waysCache = new HashMap<>();

    private static int ways(int n) {
        if (waysCache.containsKey(n)) {
            return waysCache.get(n);
        }
        if (n < 0) {
            return 0;
        }
        if (n < 4) {
            return 1;
        }
        int result = 0;
        result += ways(n - 4);
        result += ways(n - 1);
        waysCache.put(n, result);
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                int result = RedJohnIsBack.redJohn(n);

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
