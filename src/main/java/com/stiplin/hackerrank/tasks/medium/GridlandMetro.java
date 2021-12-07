package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GridlandMetro {

    /*
     * Complete the 'gridlandMetro' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. INTEGER k
     *  4. 2D_INTEGER_ARRAY track
     */

    public static long gridlandMetro(long n, long m, int k, List<List<Integer>> track) {
        Map<Integer, NavigableMap<Integer, Integer>> rows = new HashMap<>();
        long result = m * n;
        for (List<Integer> row : track) {
            int rowNum = row.get(0) - 1;
            int left = row.get(1);
            int right = row.get(2);
            NavigableMap<Integer, Integer> rowInfo = rows.computeIfAbsent(rowNum, k1 -> new TreeMap<>());
            Map.Entry<Integer, Integer> existsFloorRange = rowInfo.floorEntry(left);
            Map.Entry<Integer, Integer> existsCeilingRange = rowInfo.ceilingEntry(left);

            if (existsFloorRange != null &&
                    rangeIntersects(left, right, existsFloorRange.getKey(), existsFloorRange.getValue()) &&
                    existsCeilingRange != null &&
                    rangeIntersects(left, right, existsCeilingRange.getKey(), existsCeilingRange.getValue())
            ) {
                rowInfo.remove(existsFloorRange.getKey());
                rowInfo.remove(existsCeilingRange.getKey());
                rowInfo.put(
                        Math.min(Math.min(left, existsFloorRange.getKey()), existsCeilingRange.getKey()),
                        Math.max(Math.max(right, existsFloorRange.getValue()), existsCeilingRange.getValue())
                );
            } else {
                if (existsFloorRange != null &&
                        rangeIntersects(left, right, existsFloorRange.getKey(), existsFloorRange.getValue())) {
                    rowInfo.remove(existsFloorRange.getKey());
                    rowInfo.put(
                            Math.min(left, existsFloorRange.getKey()),
                            Math.max(right, existsFloorRange.getValue())
                    );
                } else if (existsCeilingRange != null &&
                        rangeIntersects(left, right, existsCeilingRange.getKey(), existsCeilingRange.getValue())) {
                    rowInfo.remove(existsCeilingRange.getKey());
                    rowInfo.put(
                            Math.min(left, existsCeilingRange.getKey()),
                            Math.max(right, existsCeilingRange.getValue())
                    );
                } else {
                    rowInfo.put(left, right);
                }
            }
        }
        for (Map<Integer, Integer> rowInfo : rows.values()) {
            for (Map.Entry<Integer, Integer> rangeRowInfo : rowInfo.entrySet()) {
                result -= rangeRowInfo.getValue() - rangeRowInfo.getKey() + 1;
            }
        }
        return result;
    }

    static boolean rangeIntersects(int firstLeft, int firstRight, int secondLeft, int secondRight) {
        return firstLeft <= secondRight && firstRight >= secondLeft;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        long n = Long.parseLong(firstMultipleInput[0]);

        long m = Long.parseLong(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> track = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                track.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = GridlandMetro.gridlandMetro(n, m, k, track);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();


    }
}