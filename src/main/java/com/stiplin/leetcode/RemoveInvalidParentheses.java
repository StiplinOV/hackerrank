package com.stiplin.leetcode;

import java.util.*;

public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = s.toCharArray();
        int parenthesesCount = 0;
        for (char symbol : chars) {
            if (symbol == '(') {
                parenthesesCount++;
            }
            if (symbol == ')') {
                if (parenthesesCount > 0) {
                    parenthesesCount--;
                } else {
                    stringBuffer.append(symbol);
                }
            }
        }
        while (parenthesesCount != 0) {
            stringBuffer.append("(");
            parenthesesCount--;
        }
        Set<String> result = new HashSet<>();
        loop(0, chars, stringBuffer, result, new Stack<>());
        if (result.isEmpty()) {
            result.add("");
        }
        return new ArrayList<>(result);
    }

    private static void loop(int startIndex, char[] chars, StringBuffer parenthesesSequence, Set<String> resultSet, Stack<Integer> integers) {
        if (parenthesesSequence.length() == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                if (!integers.contains(i)) {
                    sb.append(chars[i]);
                }
            }
            if (isValid(sb)) {
                resultSet.add(sb.toString());
            }
            return;
        }
        for (int i = startIndex; i < chars.length; i++) {
            char symbol = chars[i];
            if (symbol == parenthesesSequence.charAt(0)) {
                integers.push(i);
                loop(i + 1, chars, new StringBuffer(parenthesesSequence.subSequence(1, parenthesesSequence.length())), resultSet, integers);
                integers.pop();
            }
        }
    }

    private static boolean isValid(StringBuilder str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            char symbol = str.charAt(i);
            if (symbol == '(') {
                cnt++;
            }
            if (symbol == ')') {
                if (cnt > 0) {
                    cnt--;
                } else {
                    return false;
                }
            }
        }
        return cnt == 0;
    }

}
