package com.stiplin.hackerrank.tasks.easy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FlatlandSpaceStations {

    // Complete the flatlandSpaceStations function below.
    static int flatlandSpaceStations(int n, int[] c) {
        Arrays.sort(c);
        int result = 0;
        int firstCity = c[0];
        int lastCity = c[c.length - 1];
        if (result < firstCity) {
            result = firstCity;
        }
        if (result < (n - lastCity - 1)) {
            result = n - lastCity - 1;
        }
        int prevCity = firstCity;
        for (int i = 1; i < c.length; i++) {
            int currentCity = c[i];
            if (result < (currentCity - prevCity) / 2) {
                result = (currentCity - prevCity) / 2;
            }
            prevCity = currentCity;
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] c = new int[m];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = flatlandSpaceStations(n, c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
