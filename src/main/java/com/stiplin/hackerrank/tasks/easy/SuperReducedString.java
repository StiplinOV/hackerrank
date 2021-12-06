package com.stiplin.hackerrank.tasks.easy;

import java.io.*;

public class SuperReducedString {

    /*
     * Complete the 'superReducedString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String superReducedString(String s) {
        // Write your code here
        if (s.length() < 2) {
            return s;
        }
        StringBuilder buffer = new StringBuilder(s);
        char prev = buffer.charAt(0);
        for (int i = 1; i < buffer.length(); i++) {
            char current = buffer.charAt(i);
            if (current == prev) {
                int offsetToLeft = i - 1;
                int offsetToRight = i;
                while (offsetToLeft >= 0 && offsetToRight < buffer.length()) {
                    char offsetToLeftChar = buffer.charAt(offsetToLeft);
                    char offsetToRightChar = buffer.charAt(offsetToRight);
                    if (offsetToLeftChar == '$') {
                        offsetToLeft--;
                        continue;
                    }
                    if (offsetToLeftChar != offsetToRightChar) {
                        break;
                    }
                    buffer.setCharAt(offsetToLeft, '$');
                    buffer.setCharAt(offsetToRight, '$');
                    offsetToRight++;
                    offsetToLeft--;
                }
                i = offsetToRight;
                if (i < buffer.length()) {
                    current = buffer.charAt(i);
                }
            }
            prev = current;
        }
        for (int i = buffer.length() - 1; i >= 0; i--) {
            if (buffer.charAt(i) == '$') {
                buffer.deleteCharAt(i);
            }
        }
        if (buffer.length() == 0) {
            return "Empty String";
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = SuperReducedString.superReducedString(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
