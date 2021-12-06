package com.stiplin.hackerrank.tasks.hard;

import java.io.*;
import java.util.stream.IntStream;

public class MorganAndString {

    /*
     * Complete the 'morganAndString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    public static String morganAndString(String firstString, String secondString) {
        final int firstStringLength = firstString.length();
        final int secondStringLength = secondString.length();
        StringBuilder result = new StringBuilder();
        int firstIndex = 0;
        int secondIndex = 0;
        while (firstIndex < firstStringLength && secondIndex < secondStringLength) {
            if (firstString.charAt(firstIndex) < secondString.charAt(secondIndex)) {
                result.append(firstString.charAt(firstIndex));
                firstIndex++;
            }else if (firstString.charAt(firstIndex) > secondString.charAt(secondIndex)) {
                result.append(secondString.charAt(secondIndex));
                secondIndex++;
            }else {
                if (compare(firstString, firstIndex + 1, secondString, secondIndex + 1)) {
                    result.append(firstString.charAt(firstIndex));
                    firstIndex++;
                    while (firstIndex < firstString.length() && firstString.charAt(firstIndex) == firstString.charAt(firstIndex - 1)) {
                        result.append(firstString.charAt(firstIndex));
                        firstIndex++;
                    }
                } else {
                    result.append(secondString.charAt(secondIndex));
                    secondIndex++;
                    while (secondIndex < secondString.length() && secondString.charAt(secondIndex) == secondString.charAt(secondIndex - 1)) {
                        result.append(secondString.charAt(secondIndex));
                        secondIndex++;
                    }
                }
            }
        }

        if (firstIndex < firstStringLength) {
            result.append(firstString.substring(firstIndex));
        }

        if (secondIndex < secondStringLength) {
            result.append(secondString.substring(secondIndex));
        }

        return result.toString();
    }

    private static boolean compare(String firstString, int firstIndex, String secondString, int secondIndex) {
        while (firstIndex < firstString.length() && secondIndex < secondString.length()) {
            if (firstString.charAt(firstIndex) < secondString.charAt(secondIndex)) {
                return true;
            }
            if (firstString.charAt(firstIndex) > secondString.charAt(secondIndex)) {
                return false;
            }
            firstIndex++;
            secondIndex++;
        }

        return firstIndex != firstString.length();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();

                String result = MorganAndString.morganAndString(a, b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

}
