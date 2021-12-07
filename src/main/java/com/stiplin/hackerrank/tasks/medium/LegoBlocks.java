package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.stream.IntStream;

public class LegoBlocks {

    /*
     * Complete the 'legoBlocks' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     */

    //ways
    static long[] numberOfWaysForOneRowForWidth = new long[1001];

    static {
        numberOfWaysForOneRowForWidth[0] = 1;
        numberOfWaysForOneRowForWidth[1] = 1;
        numberOfWaysForOneRowForWidth[2] = 2;
        numberOfWaysForOneRowForWidth[3] = 4;
        long lastFour = numberOfWaysForOneRowForWidth[3] + numberOfWaysForOneRowForWidth[2] + numberOfWaysForOneRowForWidth[1] + numberOfWaysForOneRowForWidth[0];
        for (int i = 4; i < numberOfWaysForOneRowForWidth.length; i++) {
            numberOfWaysForOneRowForWidth[i] = lastFour;
            lastFour = (lastFour - numberOfWaysForOneRowForWidth[i - 4] + numberOfWaysForOneRowForWidth[i] + 1000000007) % 1000000007;
        }
    }

    static int legoBlocks(int height, int width) {
        long[] numberOfUnbreakableWallsForWidth = new long[width + 1];
        long[] numberOfBrokenWallsForWidth = new long[width + 1];
        numberOfUnbreakableWallsForWidth[0] = 0;
        numberOfBrokenWallsForWidth[0] = 1;
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j < i; j++) {
                long allNumberOfWaysForWidth = (numberOfBrokenWallsForWidth[j] + numberOfUnbreakableWallsForWidth[j]) % 1000000007;

                numberOfBrokenWallsForWidth[i] = (numberOfBrokenWallsForWidth[i] + allNumberOfWaysForWidth * numberOfUnbreakableWallsForWidth[i - j]) % 1000000007;
            }
            long allNumberOfWays = pow(numberOfWaysForOneRowForWidth[i], height);
            numberOfUnbreakableWallsForWidth[i] = (allNumberOfWays - numberOfBrokenWallsForWidth[i] + 1000000007) % 1000000007;
        }
        return (int) numberOfUnbreakableWallsForWidth[width];
    }

    static long pow(long from, long to) {
        long result = 1;
        for (int i = 0; i < to; i++) {
            result = (result * from) % 1000000007;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int result = LegoBlocks.legoBlocks(n, m);

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
