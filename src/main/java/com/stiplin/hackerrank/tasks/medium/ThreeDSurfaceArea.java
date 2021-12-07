package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ThreeDSurfaceArea {

    /*
     * Complete the 'surfaceArea' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY A as parameter.
     */

    public static int surfaceArea(List<List<Integer>> A) {
        int width = A.size();
        int height = A.get(0).size();
        int result = (width * height) * 2;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int cur = A.get(i).get(j);
                int up = up(i, j, A);
                int down = down(i, j, A);
                int left = left(i, j, A);
                int right = right(i, j, A);
                result += subtract(cur, up);
                result += subtract(cur, down);
                result += subtract(cur, left);
                result += subtract(cur, right);
            }
        }
        return result;
    }

    private static int subtract(int a, int b) {
        if (a < b) {
            return 0;
        }
        return a - b;
    }

    private static int up(int i, int j, List<List<Integer>> A) {
        if (j == 0) {
            return 0;
        }
        return A.get(i).get(j - 1);
    }

    private static int down(int i, int j, List<List<Integer>> A) {
        int height = A.get(0).size();
        if (j == height - 1) {
            return 0;
        }
        return A.get(i).get(j + 1);

    }

    private static int right(int i, int j, List<List<Integer>> A) {
        int width = A.size();
        if (i == width - 1) {
            return 0;
        }
        return A.get(i + 1).get(j);
    }

    private static int left(int i, int j, List<List<Integer>> A) {
        if (i == 0) {
            return 0;
        }
        return A.get(i - 1).get(j);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int H = Integer.parseInt(firstMultipleInput[0]);

        int W = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> A = new ArrayList<>();

        IntStream.range(0, H).forEach(i -> {
            try {
                A.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = ThreeDSurfaceArea.surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
