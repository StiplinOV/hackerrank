package com.stiplin.hackerrank.tasks.easy;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class WeightedUniformStrings {

    /*
     * Complete the 'weightedUniformStrings' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER_ARRAY queries
     */

    public static List<String> weightedUniformStrings(String s, List<Integer> queriesParam) {
        List<String> result = new ArrayList<>(queriesParam.size());
        Map<Integer, Set<Integer>> queryIndexMap = new HashMap<>();
        for (int i = 0; i < queriesParam.size(); i++) {
            result.add("No");
            Set<Integer> indexes = queryIndexMap.get(queriesParam.get(i));
            if (indexes == null) {
                indexes = new HashSet<>();
            }
            indexes.add(i);
            queryIndexMap.put(queriesParam.get(i), indexes);
        }
        char prev = s.charAt(0);
        int weight = weight(prev);
        if (queryIndexMap.containsKey(weight)) {
            queryIndexMap.get(weight).forEach(val -> result.set(val, "Yes"));
        }
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (prev == cur) {
                if (weight < 0) {
                    weight = -1;
                } else {
                    weight += weight(cur);
                }
            } else {
                weight = weight(cur);
            }
            Set<Integer> weightIndex = queryIndexMap.get(weight);
            if (weightIndex != null) {
                weightIndex.forEach(val -> result.set(val, "Yes"));
            }
            prev = cur;
        }
        return result;
    }

    static int weight(char character) {
        return ((int) character) - (int) 'a' + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<String> result = WeightedUniformStrings.weightedUniformStrings(s, queries);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

}
