package com.stiplin.hackerrank.tasks.hard;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CuttingBoards {

    /*
     * Complete the 'boardCutting' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY cost_y
     *  2. INTEGER_ARRAY cost_x
     */

    public static int boardCutting(List<Integer> cost_y, List<Integer> cost_x) {
        // Write your code here
        long xParts = 1;
        long yParts = 1;
        int xPointer = 0;
        int yPointer = 0;
        long xSize = cost_x.size();
        long ySize = cost_y.size();
        long result = 0;

        cost_x.sort((f, s) -> s - f);
        cost_y.sort((f, s) -> s - f);

        while (xPointer < xSize || yPointer < ySize) {
            if (xPointer == xSize) {
                int yCost = cost_y.get(yPointer);
                result += yCost * yParts;
                xParts++;
                yPointer++;
            } else if (yPointer == ySize) {
                int xCost = cost_x.get(xPointer);
                result += xCost * xParts;
                yParts++;
                xPointer++;
            } else {
                int xCost = cost_x.get(xPointer);
                int yCost = cost_y.get(yPointer);

                if (xCost > yCost) {
                    result += xCost * xParts;
                    yParts++;
                    xPointer++;
                } else if (yCost > xCost) {
                    result += yCost * yParts;
                    xParts++;
                    yPointer++;
                } else {
                    if (xSize > ySize) {
                        result += xCost * xParts;
                        yParts++;
                        xPointer++;
                    } else {
                        result += yCost * yParts;
                        xParts++;
                        yPointer++;
                    }
                }
            }
        }

        return (int) (result % 1000000007);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int m = Integer.parseInt(firstMultipleInput[0]);

                int n = Integer.parseInt(firstMultipleInput[1]);

                List<Integer> cost_y = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> cost_x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                int result = CuttingBoards.boardCutting(cost_y, cost_x);

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
