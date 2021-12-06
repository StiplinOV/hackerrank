package com.stiplin.hackerrank.tasks.hard;

import java.io.*;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class InsertionSortAdvancedAnalysis {

    /*
     * Complete the 'insertionSort' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */


    public static long insertionSort(List<Integer> arr) {
        long result = 0;
        BTree btree = new BTree(arr);
        for (int element : arr) {
            result += btree.getHigherSum(element);
            btree.increment(element);
        }

        return result;
    }

    static class BTree {

        NavigableMap<Integer, Integer> indexes = new TreeMap<>();

        int[] btree;

        BTree(List<Integer> arr) {
            for (Integer element : arr) {
                indexes.put(element, 0);
            }
            int index = 0;
            for (int key : indexes.keySet()) {
                indexes.put(key, index);
                index++;
            }
            btree = new int[indexes.size()];
        }

        void increment(int value) {
            int index = indexes.get(value);
            while (index < btree.length) {
                btree[index] += 1;
                index |= index + 1;
            }
        }

        long getHigherSum(int value) {
            Integer floorIndex = indexes.floorEntry(value).getValue();
            long floorKeySum = floorIndex == null ? 0 : getSum(floorIndex);
            return getSum(btree.length - 1) - floorKeySum;
        }

        private long getSum(int index) {
            long result = 0;
            while (index >= 0) {
                result += btree[index];
                if (index == 0) {
                    break;
                }
                index &= index + 1;
                index--;
            }
            return result;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                long result = InsertionSortAdvancedAnalysis.insertionSort(arr);

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
