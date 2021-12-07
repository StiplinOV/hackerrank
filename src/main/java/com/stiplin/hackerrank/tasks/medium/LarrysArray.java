package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LarrysArray {

    /*
     * Complete the 'larrysArray' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER_ARRAY A as parameter.
     */

    public static String larrysArray(Integer[] A) {
        // Write your code here
        res = 0;
        mergeSort(A);
        return res % 2 == 0 ? "YES" : "NO";
    }

    static int res = 0;

    public static Integer[] mergeSort(Integer[] array) {
        if (array.length == 1) {
            return array;
        }
        int middle = array.length / 2;
        Integer[] leftArray = new Integer[middle];
        Integer[] rightArray = new Integer[array.length - middle];
        System.arraycopy(array, 0, leftArray, 0, middle);
        System.arraycopy(array, middle, rightArray, 0, array.length - middle);
        return merge(mergeSort(leftArray), mergeSort(rightArray));
    }

    static Integer[] merge(Integer[] left, Integer[] right) {
        Integer[] result = new Integer[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                res += left.length - leftIndex;
                result[resultIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
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

                Integer[] A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);

                String result = LarrysArray.larrysArray(A);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

}
