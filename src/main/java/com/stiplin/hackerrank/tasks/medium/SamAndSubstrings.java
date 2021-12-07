package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class SamAndSubstrings {

    /*
     * Complete the 'substrings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING n as parameter.
     */
    public static long substrings(String n) {
        // Write your code here
        int size = n.length();
        long result = 0;
        Map<Long, long[]> powsMap = new HashMap<>();
        for (int i = 0; i < n.length(); i++) {
            long digit = n.charAt(i) - '1' + 1;
            long[] powsArray = powsMap.get(digit);
            if (powsArray == null) {
                powsArray = new long[size];
                long pow = 0;
                for (int j = 0; j < size; j++) {
                    pow = (pow + (digit * (pow(j) % 1000000007)) % 1000000007) % 1000000007;
                    powsArray[j] = pow;
                }
                powsMap.put(digit, powsArray);
            }
            long localResult = powsArray[size - 1 - i];

            result = (result + (localResult * (i + 1)) % 1000000007) % 1000000007;
        }
        return result % 1000000007;
    }

    private static NavigableMap<Integer, Long> powsTen = new TreeMap<>();

    private static long pow(int j) {
        if (powsTen.isEmpty()) {
            powsTen.put(0, 1L);
        }
        if (powsTen.containsKey(j)) {
            return powsTen.get(j);
        }
        int higherKey = powsTen.lastKey();
        long result = powsTen.get(higherKey);
        for (int i = higherKey; i < j; i++) {
            result = (result * 10) % 1000000007;
            powsTen.put(i + 1, result);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String n = bufferedReader.readLine();

        long result = SamAndSubstrings.substrings(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
