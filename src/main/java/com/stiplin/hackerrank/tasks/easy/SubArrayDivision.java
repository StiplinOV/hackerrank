package com.stiplin.hackerrank.tasks.easy;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SubArrayDivision {

    // Complete the birthday function below.
    static int birthday(List<Integer> s, int d, int m) {
        int result = 0;
        int startSum = getSum(s, m);

        if (startSum == d) {
            result++;
        }

        for (int i = m; i < s.size(); i++) {
            startSum = startSum - s.get(i - m) + s.get(i);
            if (startSum == d) {
                result++;
            }
        }

        return result;
    }

    static int getSum(List<Integer> list, int count) {
        int result = 0;
        for (int i = 0; i < count; i++) {
            result += list.get(i);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        String[] dm = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int d = Integer.parseInt(dm[0]);

        int m = Integer.parseInt(dm[1]);

        int result = birthday(s, d, m);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
