package com.stiplin.leetcode;

import java.util.Stack;

public class ValidParentheses {
    private boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char symbol : chars) {
            switch (symbol) {
                case '(':
                case '[':
                case '{':
                    stack.push(symbol);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char last = stack.pop();
                    if (last != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    last = stack.pop();
                    if (last != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    last = stack.pop();
                    if (last != '{') {
                        return false;
                    }
                    break;
                default:
                    throw new RuntimeException("wrong bracket");
            }
        }
        return stack.isEmpty();
    }

}
