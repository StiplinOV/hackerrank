package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MaximumPalindromes {

    /*
     * Complete the 'initialize' function below.
     *
     * The function accepts STRING s as parameter.
     */

    static Map<String, Integer> cache = new HashMap<>();

    /*
     * Complete the 'initialize' function below.
     *
     * The function accepts STRING s as parameter.
     */

    static Map<Long, Long> factrCache = new HashMap<>();

    static long fast_pow(long base, long n, long M) {
        if (n == 0)
            return 1;
        if (n == 1)
            return base;
        long halfn = fast_pow(base, n / 2, M);
        if (n % 2 == 0)
            return (halfn * halfn) % M;
        else
            return (((halfn * halfn) % M) * base) % M;
    }

    static long findMMI_fermat(long n, int M) {
        if (!factrCache.containsKey(n)) {
            factrCache.put(n, fast_pow(n, M - 2, M));
        }
        return factrCache.get(n);
    }

    private static int[][] matrix;

    public static void initialize(String s) {
        matrix = new int['z' - 'a' + 1][s.length()];
        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (i > 0) {
                for (int j = 'a'; j <= 'z'; j++) {
                    matrix[j - 'a'][i] = matrix[j - 'a'][i - 1];
                }
            }
            matrix[symbol - 'a'][i] = matrix[symbol - 'a'][i] + 1;
        }
    }

    static Map<Integer, Map<Integer, Integer>> answers = new HashMap<>();

    public static int answerQuery(int l, int r) {
        Map<Integer, Integer> answerValue = answers.get(l);
        if (answerValue != null) {
            Integer result = answerValue.get(r);
            if (result != null) {
                return result;
            }
        }

        int[] vector = new int['z' - 'a' + 1];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = matrix[i][r - 1];
        }
        if (l > 1) {
            for (int i = 0; i < vector.length; i++) {
                vector[i] = vector[i] - matrix[i][l - 2];
            }
        }
        //System.out.println(Arrays.toString(vector));
        int firstMultiplier = 0;
        int dividedCount = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] % 2 == 1) {
                firstMultiplier++;
            }
            vector[i] = vector[i] / 2;
            dividedCount = dividedCount + vector[i];
        }

        if (firstMultiplier == 0) {
            firstMultiplier = 1;
        }
        long secondMultiplier = modedFactr(dividedCount);
        long denominator = 1;
        for (long value : vector) {
            secondMultiplier = (secondMultiplier * findMMI_fermat(modedFactr(value), 1000000007)) % 1000000007;
        }
        // secondMultiplier = (secondMultiplier*findMMI_fermat(denominator, 1000000007))% 1000000007;

        // Return the answer for this query modulo 1000000007.
        int result = (int) ((firstMultiplier % 1000000007 * secondMultiplier % 1000000007) % 1000000007);
        if (answerValue == null) {
            answerValue = new HashMap<>();
        }
        answerValue.put(r, result);
        return result;
    }

    static Map<Long, Long> modedfactrCache = new HashMap<>();


    static long modedFactr(long n) {
        if (modedfactrCache.containsKey(n)) {
            return modedfactrCache.get(n);
        }
        long result = 1;
        for (long i = 1; i <= n; i++) {
            result = (result * i) % 1000000007;
        }
        modedfactrCache.put(n, result);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        MaximumPalindromes.initialize(s);

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int l = Integer.parseInt(firstMultipleInput[0]);

                int r = Integer.parseInt(firstMultipleInput[1]);

                int result = MaximumPalindromes.answerQuery(l, r);

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
