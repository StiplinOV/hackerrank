package com.stiplin.hackerrank.tasks.medium;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class SherlockAndAnagrams {

    /*
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int sherlockAndAnagrams(String sParam) {
        StringBuilder s = new StringBuilder(sParam);
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            Map<String, Integer> strings = new HashMap<>();
            for (int left = 0; left <= s.length() - i; left++) {
                int right = left + i;
                char[] chars = s.substring(left, right).toCharArray();
                Arrays.sort(chars);
                String substring = new String(chars);
                if (strings.containsKey(substring)) {
                    result += strings.get(substring);
                }
                strings.merge(substring, 1, Integer::sum);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = SherlockAndAnagrams.sherlockAndAnagrams(s);

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
