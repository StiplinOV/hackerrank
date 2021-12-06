package com.stiplin.hackerrank.tasks.hard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class TwoTwo {


    private static final PowTwoTrie powTwoTrie = new PowTwoTrie();

    private static class PowTwoTrie {

        private static final int MAX_POW = 801;

        final Map<Character, Vertex> childs = new HashMap<>();

        final BigInteger TWO = BigInteger.valueOf(2);

        public PowTwoTrie() {
            for (int i = 0; i < MAX_POW; i++) {
                this.addString(TWO.pow(i).toString());
            }
        }

        public void addString(String value) {
            char[] chars = value.toCharArray();

            if (chars.length == 0) {
                return;
            }

            char current = chars[0];
            Vertex temp = childs.get(current);
            if (temp == null) {
                temp = new Vertex(current);
                childs.put(current, temp);
            }

            for (int i = 1; i < chars.length; i++) {
                current = chars[i];
                temp.appendChildIfNotExists(current);
                temp = temp.getChild(current);
            }
        }

        public int getPowerOfTwoCount(char[] charArray) {
            int result = 0;

            for (int i = 0; i < charArray.length; i++) {
                result += getPowerOfTwoCount(charArray, i);
            }

            return result;
        }

        public int getPowerOfTwoCount(char[] charArray, int indexFrom) {
            int result = 0;

            if (charArray.length == 0) {
                return result;
            }

            Vertex temp = childs.get(charArray[indexFrom]);
            if (temp == null) {
                return result;
            }
            if (temp.isPowerOfTwo()) {
                result++;
            }

            for (int i = indexFrom + 1; i < charArray.length; i++) {
                temp = temp.getChild(charArray[i]);

                if (temp == null) {
                    return result;
                }
                if (temp.isPowerOfTwo()) {
                    result++;
                }
            }

            return result;
        }

        private static class Vertex {
            final Vertex parent;
            final char value;
            final Map<Character, Vertex> childs = new HashMap<>();

            private Boolean isPowerOfTwo;

            Vertex(char value) {
                this(null, value);
            }

            Vertex(Vertex parent, char value) {
                this.parent = parent;
                this.value = value;
            }

            void appendChildIfNotExists(char childChar) {
                if (!hasChild(childChar)) {
                    Vertex child = new Vertex(this, childChar);
                    this.childs.put(childChar, child);
                }
            }

            boolean hasChild(char childChar) {
                return this.childs.containsKey(childChar);
            }

            Vertex getChild(char childChar) {
                return this.childs.get(childChar);
            }

            private boolean isPowerOfTwo() {
                if (isPowerOfTwo == null) {
                    BigInteger number = new BigInteger(this.asString());

                    isPowerOfTwo = number.compareTo(BigInteger.ZERO) > 0 && (number.and(number.subtract(BigInteger.ONE)).compareTo(BigInteger.ZERO) == 0);
                }
                return isPowerOfTwo;
            }

            boolean isRoot() {
                return parent == null;
            }

            boolean isTerminal() {
                return this.childs.isEmpty();
            }

            public String asString() {
                Vertex temp = this;
                Stack<Character> characters = new Stack<>();
                characters.push(temp.value);
                while (!temp.isRoot()) {
                    temp = temp.parent;
                    characters.push(temp.value);
                }

                char[] chars = new char[characters.size()];

                for (int i = 0; !characters.isEmpty(); i++) {
                    chars[i] = characters.pop();
                }

                return new String(chars);
            }

            @Override
            public String toString() {
                return parent + " " + value;
            }
        }

    }

    /*
     * Complete the twoTwo function below.
     */
    static int twoTwo(String a) {
        return powTwoTrie.getPowerOfTwoCount(a.toCharArray());
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(scanner.nextLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String a = scanner.nextLine();

            int result = twoTwo(a);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
