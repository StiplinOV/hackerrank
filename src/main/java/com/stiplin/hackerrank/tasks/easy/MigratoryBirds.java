package com.stiplin.hackerrank.tasks.easy;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MigratoryBirds {

    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            Integer type = arr.get(i);
            Integer count = countMap.get(type);
            if (count == null) {
                count = 0;
            }
            countMap.put(type, count + 1);
        }

        Integer maxKey = 0;
        Integer maxValue = 0;
        for (Integer key : countMap.keySet()) {
            Integer count = countMap.get(key);
            if (maxValue < count) {
                maxValue = count;
                maxKey = key;
            }
        }

        return maxKey;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
