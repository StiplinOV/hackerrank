package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LilysHomework {

    /*
     * Complete the 'lilysHomework' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int lilysHomework(List<Integer> arr) {
        // Write your code here
        Map<Integer, Integer> indexMap = createIndexMap(arr);
        Map<Integer, Integer> inverseMap = createInverseIndexMap(arr);
        Collections.sort(arr);
        List<Integer> sortedArray = new ArrayList<>(arr);
        Collections.reverse(arr);
        List<Integer> reverseArray = new ArrayList<>(arr);

        return Math.min(lilysHomework(indexMap, inverseMap, sortedArray), lilysHomework(indexMap, inverseMap, reverseArray));
    }

    public static int lilysHomework(Map<Integer, Integer> indexMapParam, Map<Integer, Integer> inverseMapParam, List<Integer> sortedArray) {
        Map<Integer, Integer> indexMap = new HashMap<>(indexMapParam);
        Map<Integer, Integer> inverseMap = new HashMap<>(inverseMapParam);
        // Write your code here
        int result = 0;
        for (int i = 0; i < sortedArray.size(); i++) {
            int element = sortedArray.get(i); //1
            int elementIndex = indexMap.get(element);  //5
            int swapElement = inverseMap.get(i); // 3
            if (elementIndex != i) {
                indexMap.put(element, i);
                indexMap.put(swapElement, elementIndex);
                inverseMap.put(i, element);
                inverseMap.put(elementIndex, swapElement);
                result++;
            }
        }
        return result;
    }

    private static Map<Integer, Integer> createIndexMap(List<Integer> arr) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            result.put(arr.get(i), i);
        }
        return result;
    }

    private static Map<Integer, Integer> createInverseIndexMap(List<Integer> arr) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            result.put(i, arr.get(i));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = LilysHomework.lilysHomework(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
