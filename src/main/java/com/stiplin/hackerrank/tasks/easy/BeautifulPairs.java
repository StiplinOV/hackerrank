package com.stiplin.hackerrank.tasks.easy;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BeautifulPairs {

    /*
     * Complete the 'beautifulPairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY A
     *  2. INTEGER_ARRAY B
     */

    public static int beautifulPairs(List<Integer> A, List<Integer> B) {
        // Write your code here
        Map<Integer, Integer> firstMap = new HashMap<>();
        A.forEach(val -> firstMap.merge(val, 1, Integer::sum));
        Map<Integer, Integer> secondMap = new HashMap<>();
        B.forEach(val -> secondMap.merge(val, 1, Integer::sum));
        int firstSetLen = A.size();//4
        int secondSetLen = B.size();//4
        Map<Integer, Integer> intersectMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : secondMap.entrySet()) {
            if (firstMap.containsKey(entry.getKey())) {
                intersectMap.put(entry.getKey(), Math.min(firstMap.get(entry.getKey()), entry.getValue()));
            }
        }
        int size = size(intersectMap);
        if (firstMap.equals(secondMap)) {
            return size - 1;
        }
        if (size < Math.min(firstSetLen, secondSetLen)) {
            return size + 1;
        }
        return size;
    }

    private static int size(Map<Integer, Integer> map) {
        return map.values().stream().reduce(Integer::sum).get();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> B = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = BeautifulPairs.beautifulPairs(A, B);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
