package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class HackerlandRadioTransmitters {

    /*
     * Complete the 'hackerlandRadioTransmitters' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY x
     *  2. INTEGER k
     */

    public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
        // Write your code here
        int result = 0;
        NavigableSet<Integer> houses = new TreeSet<>(x);
        Integer leftUntransmittedHouse = houses.first();
        while (leftUntransmittedHouse != null) {
            result++;
            int nextIdealPosition = leftUntransmittedHouse + k;
            int transmitterPosition = houses.floor(nextIdealPosition);
            leftUntransmittedHouse = houses.higher(transmitterPosition + k);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = HackerlandRadioTransmitters.hackerlandRadioTransmitters(x, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
