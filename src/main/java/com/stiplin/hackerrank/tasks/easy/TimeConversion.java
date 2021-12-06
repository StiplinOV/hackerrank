package com.stiplin.hackerrank.tasks.easy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TimeConversion {

    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        if (s.equals("12:00:00AM")) {
            return "00:00:00";
        }
        if (s.equals("12:00:00PM")) {
            return "12:00:00";
        }
        /*
         * Write your code here.
         */
        String ampm = s.substring(s.length() - 2);
        int hour = Integer.valueOf(s.substring(0, 2));
        if ("AM".equals(ampm)) {
            if (hour == 12) {
                return "00" + s.substring(2, s.length() - 2);
            }
            return s.substring(0, s.length() - 2);
        } else {
            if (hour == 12) {
                return "12" + s.substring(2, s.length() - 2);
            }
            hour = hour + 12;
            if (hour == 24) {
                hour = 0;
            }
            return hour + s.substring(2, s.length() - 2);
        }
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scan.nextLine();

        String result = timeConversion(s);

        bw.write(result);
        bw.newLine();

        bw.close();
    }
}
