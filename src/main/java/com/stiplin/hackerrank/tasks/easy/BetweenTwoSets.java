package com.stiplin.hackerrank.tasks.easy;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BetweenTwoSets {

    /*
     * Complete the 'getTotalX' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        int min = getMax(a);
        int max = getMin(b);
        int result = 0;
        for (int i = min; i <= max; i++) {
            if (isFactorOf(i, a) && isFactorFor(b, i)) {
                result++;
            }
        }
        return result;
    }

    private static int getMin(List<Integer> list) {
        int result = list.get(0);

        for (int value : list) {
            if (value < result) {
                result = value;
            }
        }

        return result;
    }

    private static int getMax(List<Integer> list) {
        int result = list.get(0);

        for (int value : list) {
            if (value > result) {
                result = value;
            }
        }

        return result;
    }

    //Список мальеньких чисел
    private static boolean isFactorOf(int number, List<Integer> factors) {
        boolean result = true;
        for (int factor : factors) {
            if (number % factor != 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    //Список больших чисел
    private static boolean isFactorFor(List<Integer> numbers, int factor) {
        boolean result = true;
        for (int number : numbers) {
            if (number % factor != 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int total = BetweenTwoSets.getTotalX(arr, brr);

        bufferedWriter.write(String.valueOf(total));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
