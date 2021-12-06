package com.stiplin.hackerrank.tasks.easy;

import java.io.*;
import java.util.*;


class Pair {
    char left;
    char right;

    Pair(char left, char right) {
        this.left = left;
        this.right = right;
    }

    public int hashCode() {
        return left + right;
    }

    public boolean equals(Object other) {
        Pair o = (Pair) other;
        return (o.left == left && o.right == right) || (o.right == left && o.left == right);
    }

}

public class TwoCharacters {

    /*
     * Complete the 'alternate' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int alternate(String s) {
        if (s.length() == 2 && s.charAt(0) != s.charAt(1)) {
            return 2;
        }
        // Write your code here
        StringBuilder stringBuilder = createNormalizeStringBuilder(s);
        Map<Character, Integer> counts = getCounts(stringBuilder);
        Map<Character, Set<Character>> availableCharsMap = getAvailableCharsMap(stringBuilder, counts.keySet());
        Set<Pair> pairs = reduceToPairs(availableCharsMap);
        int result = 0;
        for (Pair pair : pairs) {
            int count = counts.get(pair.left) + counts.get(pair.right);
            if (count > result) {
                result = count;
            }
        }
        return result;
    }

    static StringBuilder createNormalizeStringBuilder(String s) {
        StringBuilder result = new StringBuilder(s);
        char prev = 0;
        Set<Character> charsToBeDeleted = new HashSet<>();
        for (int i = result.length() - 1; i >= 0; i--) {
            char cur = result.charAt(i);
            if (prev == cur) {
                charsToBeDeleted.add(cur);
            }
            prev = cur;
        }
        for (int i = result.length() - 1; i >= 0; i--) {
            if (charsToBeDeleted.contains(result.charAt(i))) {
                result.deleteCharAt(i);
            }
        }
        return result;
    }

    static Map<Character, Integer> getCounts(StringBuilder stringBuilder) {
        Map<Character, Integer> result = new HashMap<Character, Integer>();
        for (int i = 0; i < stringBuilder.length(); i++) {
            result.merge(stringBuilder.charAt(i), 1, (v1, v2) -> v1 + v2);
        }
        return result;
    }

    static Map<Character, Set<Character>> getAvailableCharsMap(StringBuilder stringBuilder, Set<Character> alphabet) {
        //System.out.println(stringBuilder);
        Map<Character, Set<Character>> result = new HashMap<>();
        List<Character> alphabetList = new ArrayList<>(alphabet);
        char[][] arr = new char[alphabet.size()][alphabet.size()];
        for (int i = 0; i < stringBuilder.length(); i++) {
            char character = stringBuilder.charAt(i);
            int index = alphabetList.indexOf(character);
            if (arr[index][index] != 0) {
                Set<Character> availableValues = result.get(character);
                Set<Character> currentValues = new HashSet<>();
                for (int j = 0; j < alphabet.size(); j++) {
                    if (arr[index][j] != character && arr[index][j] != 0) {
                        currentValues.add(arr[index][j]);
                    }
                }
                if (availableValues == null) {
                    result.put(character, currentValues);
                } else {
                    availableValues.retainAll(currentValues);
                }
            }
            for (int j = 0; j < alphabet.size(); j++) {
                arr[j][index] = character;
                arr[index][j] = character;
            }
//            for(char[] arr1: arr) {
//                System.out.println(Arrays.toString(arr1));
//            }
//            System.out.println(result);
        }
        return result;
    }

    static Set<Pair> reduceToPairs(Map<Character, Set<Character>> availableCharsMap) {
        Set<Pair> result = new HashSet<>();
        for (char key : availableCharsMap.keySet()) {
            for (char value : availableCharsMap.get(key)) {
                Set<Character> reverseSet = availableCharsMap.get(value);
                if (reverseSet != null && reverseSet.contains(key)) {
                    result.add(new Pair(key, value));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = TwoCharacters.alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
