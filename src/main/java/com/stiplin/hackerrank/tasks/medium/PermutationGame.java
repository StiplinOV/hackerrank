package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PermutationGame {

    /*
     * Complete the 'permutationGame' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    private static int[][] combinations(int m, int n) {//m = 2 n = 4
        long numberOfCombinations = (factorial(n)) / (factorial(m) * factorial(n - m));
        int[][] result = new int[(int) numberOfCombinations][m];
        int[] prevCombination = new int[m];
        for (int i = 0; i < m; i++) {
            prevCombination[i] = i + 1;
        }
        result[0] = prevCombination;
        for (int i = 1; i < numberOfCombinations; i++) {
            int[] nextCombination = arrayCopy(prevCombination);
            for (int j = m - 1; j >= 0; j--) {
                int expected = n - (m - j) + 1;
                if (nextCombination[j] == expected) {
                    continue;
                }
                if (nextCombination[j] < expected) {
                    nextCombination[j]++;
                    if (i == numberOfCombinations - 1) {
                        break;
                    }
                    int base = nextCombination[j];
                    for (int k = j + 1; k < m; k++) {
                        nextCombination[k] = ++base;
                    }
                    break;
                }
            }
            result[i] = nextCombination;
            prevCombination = nextCombination;
        }
        return result;
    }

    public static String permutationGame(List<Integer> arrParam) {
        int[] arr = ranked(listToArr(arrParam));
        for (int i = 8; i <= arr.length; i++) {
            int[][] combinations = combinations(arr.length - i, arr.length);
            for (int j = 0; j < combinations.length; j++) {
                int[] ranked = ranked(arrayCopy(arr, combinations[j]));
                addToCache(ranked);
            }
        }
        addToCache(arr);
        return cache.get(variantToIndex(arr)) ? "Alice" : "Bob";
    }

    private static int[] listToArr(List<Integer> arr) {
        int[] result = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            result[i] = arr.get(i);
        }
        return result;
    }

    static Map<Long, Boolean> cache = new HashMap<>();

    private static long variantToIndex(int[] variant) {
        long result = 0;
        for (int i = 0; i < variant.length; i++) {
            long digit = variant[variant.length - i - 1];
            result += digit * BigInteger.valueOf(15).pow(i + 1).longValue();
        }
        return result;
    }

    public static long factorial(int n) {
        long fact = 1;
        for (long i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    private static int[][] createPermutations1toN(int n) {
        long numberOfPermutations = factorial(n);
        int[][] result = new int[(int) numberOfPermutations][n];
        int[] prevPermutation = new int[n];
        for (int i = 0; i < n; i++) {
            prevPermutation[i] = i + 1;
        }
        result[0] = prevPermutation;
        for (int i = 1; i < numberOfPermutations; i++) {
            int[] currentPermutation = arrayCopy(prevPermutation);
            int maxJ = -1;
            for (int j = 1; j < currentPermutation.length; j++) {
                if (currentPermutation[j - 1] < currentPermutation[j]) {
                    maxJ = j - 1;
                }
            }
            if (maxJ == -1) {
                continue;
            }
            int maxL = -1;
            int maxJValue = currentPermutation[maxJ];
            for (int l = maxJ + 1; l < currentPermutation.length; l++) {
                if (currentPermutation[l] > maxJValue) {
                    maxL = l;
                }
            }
            if (maxL == -1) {
                continue;
            }
            currentPermutation[maxJ] = currentPermutation[maxL];
            currentPermutation[maxL] = maxJValue;
            for (int j = 0; j < (currentPermutation.length - maxJ) / 2; j++) {
                int current = currentPermutation[maxJ + j + 1];
                currentPermutation[maxJ + j + 1] = currentPermutation[currentPermutation.length - 1 - j];
                currentPermutation[currentPermutation.length - 1 - j] = current;
            }
            result[i] = currentPermutation;
            prevPermutation = currentPermutation;
        }
        return result;
    }

    private static int[] arrayCopy(int[] array) {
        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);
        return result;
    }

    private static int[] arrayCopy(int[] array, int exceptIndex) {
        int[] result = new int[array.length - 1];
        int pointer = 0;
        for (int i = 0; i < array.length; i++) {
            if (exceptIndex == i) {
                continue;
            }
            result[pointer++] = array[i];
        }
        return result;
    }

    private static int[] arrayCopy(int[] array, int[] exceptedValuesParam) {
        int[] result = new int[array.length - exceptedValuesParam.length];
        int pointer = 0;
        Set<Integer> exceptValues = new HashSet<>();
        for (int i = 0; i < exceptedValuesParam.length; i++) {
            exceptValues.add(exceptedValuesParam[i]);
        }
        for (int i = 0; i < array.length; i++) {
            if (exceptValues.contains(array[i])) {
                continue;
            }
            result[pointer++] = array[i];
        }
        return result;
    }

    static {
        cache.put(variantToIndex(new int[]{1}), false);

        for (int i = 2; i < 8; i++) {
            int[][] p1tn = createPermutations1toN(i);
            for (int j = 0; j < p1tn.length; j++) {
                addToCache(p1tn[j]);
            }
        }
    }

    private static void addToCache(int[] currentCase) {
        long variantIndex = variantToIndex(ranked(currentCase));
        if (increasing(currentCase)) {
            cache.put(variantIndex, false);
        } else {
            boolean winer = false;
            for (int k = 0; k < currentCase.length; k++) {
                int[] check = ranked(arrayCopy(currentCase, k));
                long index = variantToIndex(check);
                try {
                    if (!cache.get(index)) {
                        winer = true;
                        break;
                    }
                } catch (Throwable ex) {
                    throw ex;
                }
            }
            cache.put(variantIndex, winer);
        }
    }

    private static int[] ranked(int[] arr) {
        int[] result = new int[arr.length];
        int[] copy = arrayCopy(arr);
        for (int i = 0; i < copy.length; i++) {
            int min = minIndex(copy);
            result[min] = i + 1;
            copy[min] = Integer.MAX_VALUE;
        }

        return result;
    }

    private static int minIndex(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[result]) {
                result = i;
            }
        }
        return result;
    }

    static boolean increasing(int[] arr) {
        return increasingDecreasing(arr, true);
    }

    static boolean increasingDecreasing(int[] arr, boolean increasing) {
        int prev = increasing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int cur : arr) {
            if (increasing && cur < prev) {
                return false;
            } else if (!increasing && cur > prev) {
                return false;
            }
            prev = cur;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                String result = PermutationGame.permutationGame(arr);

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
