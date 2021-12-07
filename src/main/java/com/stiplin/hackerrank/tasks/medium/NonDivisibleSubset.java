package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class NonDivisibleSubset {

    /*
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        // Write your code here
        int result = 0;
        int n = s.size();
        Map<Integer, Integer> modMap = createModMap(k, s);

        if (k == 1 || n == 1) {
            return 1;
        }

        if (n == 2) {
            if ((s.get(0) + s.get(1)) % k == 0) {
                return 0;
            } else {
                return 2;
            }
        }

        Integer zeroCount = modMap.get(0);
        if (zeroCount == null) {
            zeroCount = 0;
        }
        if (zeroCount == n) {
            return 0;
        } else if (zeroCount > 0) {
            result++;
        }

        boolean isEven = k % 2 == 0;

        for (int i = 1; i <= (k - 1) / 2; i++) {
            Integer count = modMap.get(i);
            if (count == null) {
                count = 0;
            }
            Integer mirrorCount = modMap.get(k - i);
            if (mirrorCount == null) {
                mirrorCount = 0;
            }
            result += Math.max(count, mirrorCount);
        }

        if (isEven) {
            Integer centralCount = modMap.get(k / 2);
            if (centralCount == null) {
                centralCount = 0;
            }
            if (centralCount == n) {
                return 0;
            } else if (centralCount > 0) {
                result++;
            }
        }

        return result;
    }

    private static Map<Integer, Integer> createModMap(int k, List<Integer> s) {
        Map<Integer, Integer> result = new HashMap<>();

        for (int element : s) {
            int mod = element % k;
            Integer modCount = result.get(mod);
            if (modCount == null) {
                modCount = 0;
            }
            result.put(mod, modCount + 1);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = NonDivisibleSubset.nonDivisibleSubset(k, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
