package com.stiplin.hackerrank.tasks.medium;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

public class BiggerIsGreater {

    private static class TreeStack {

        private NavigableMap<Character, Integer> map = new TreeMap<>();

        void push(char character) {
            Integer count = map.get(character);
            if (count == null) {
                count = 0;
            }
            map.put(character, count + 1);
        }

        Character higher(char character) {
            Character higherKey = map.higherKey(character);
            if (higherKey != null) {
                Integer count = map.get(higherKey);
                if (count == 1) {
                    map.remove(higherKey);
                } else {
                    map.put(higherKey, count - 1);
                }
            }
            return higherKey;
        }

        Character lower() {
            Character lowerKey = map.firstEntry().getKey();
            if (lowerKey != null) {
                Integer count = map.get(lowerKey);
                if (count == 1) {
                    map.remove(lowerKey);
                } else {
                    map.put(lowerKey, count - 1);
                }
            }
            return lowerKey;
        }

    }

    // Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {
        if (w.length() <= 1) {
            return "no answer";
        }

        TreeStack treeStack = new TreeStack();

        char lastSymbol = w.charAt(w.length() - 1);
        treeStack.push(lastSymbol);
        for (int i = w.length() - 2; i >= 0; i--) {
            char currentSymbol = w.charAt(i);
            treeStack.push(currentSymbol);
            if (currentSymbol < lastSymbol) {
                char[] wchars = w.toCharArray();
                wchars[i] = treeStack.higher(currentSymbol);

                for (int j = i + 1; j < w.length(); j++) {
                    wchars[j] = treeStack.lower();
                }
                return new String(wchars);
            }
            lastSymbol = currentSymbol;
        }
        return "no answer";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int TItr = 0; TItr < T; TItr++) {
            String w = scanner.nextLine();

            String result = biggerIsGreater(w);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
