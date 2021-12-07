package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

public class AbsolutePermutation {

    /*
     * Complete the 'absolutePermutation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     */

    public static List<Integer> absolutePermutation(int n, int k) {
        // Write your code here
        if (k != 0 && (n % k != 0 || !even(n/k))) {
            return Collections.singletonList(-1);
        }
        List<Integer> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(i+1);
        }
        if (k == 0) {
            return result;
        }
        for (int i = 0; i < n/(2 * k); i++) {
            for (int j = 0; j < k; j++) {
                int firstIndex = (i * (k*2)) + j;
                int secondIndex = firstIndex + k;
                int firstElement = result.get(firstIndex);
                int secondElement = result.get(secondIndex);
                result.set(secondIndex, firstElement);
                result.set(firstIndex, secondElement);
            }
        }
        return result;
    }

    public static boolean even(int number) {
        return number % 2 == 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int k = Integer.parseInt(firstMultipleInput[1]);

                List<Integer> result = AbsolutePermutation.absolutePermutation(n, k);

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
