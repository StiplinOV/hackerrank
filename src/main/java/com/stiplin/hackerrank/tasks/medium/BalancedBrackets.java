package com.stiplin.hackerrank.tasks.medium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

class BalancedBrackets {

    /*
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
    public static String isBalanced(String s) {
        Map<Character, Character> closeOpenBrackets = new HashMap<>();
        closeOpenBrackets.put(')', '(');
        closeOpenBrackets.put('}', '{');
        closeOpenBrackets.put(']', '[');
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char character : chars) {
            if (closeOpenBrackets.containsKey(character)) {
                if (stack.isEmpty()) {
                    return "NO";
                }
                char pop = stack.pop();
                if (closeOpenBrackets.get(character) != pop) {
                    return "NO";
                }
            } else {
                stack.push(character);
            }
        }
        if (stack.isEmpty()) {
            return "NO";
        }
        return "YES";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Step\\IdeaProjects\\hackerrank\\src\\main\\java\\com\\stiplin\\hackerrank\\tasks\\medium\\txt.txt"));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = BalancedBrackets.isBalanced(s);
                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

}

