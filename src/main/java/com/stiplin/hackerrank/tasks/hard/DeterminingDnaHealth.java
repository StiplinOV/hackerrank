package com.stiplin.hackerrank.tasks.hard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DeterminingDnaHealth {

    public static class Trie {

        final Map<Character, Vertex> childs;

        final int maxStart;

        final int minEnd;

        public Trie(int maxStart, int minEnd) {
            childs = new HashMap<>();
            this.maxStart = maxStart;
            this.minEnd = minEnd;
        }

        public void add(final char[] value, long health, int index) {

            if (value.length == 0) {
                return;
            }

            char current = value[0];

            Vertex temp = childs.get(current);
            if (temp == null) {
                temp = new Vertex();
                childs.put(current, temp);
            }

            for (int i = 1; i < value.length; i++) {
                current = value[i];
                temp.appendChildIfNotExists(current);
                temp = temp.getChild(current);
            }
            if (index > maxStart && index < minEnd) {
                index = (maxStart + minEnd) / 2;
            }
            temp.addHealth(index, health);
        }

    }

    public static class Vertex {

        private final NavigableMap<Integer, Long> indexHealthMap = new TreeMap<>();

        private final Map<Character, Vertex> childs = new HashMap<>();

        public long getHealth(int indexFrom, int indexTo) {
            long result = 0;
            final NavigableMap<Integer, Long> submap = indexHealthMap.subMap(indexFrom, true, indexTo, true);
            for (long v : submap.values()) {
                result += v;
            }

            return result;
        }

        public void addHealth(int index, long health) {
            this.indexHealthMap.merge(index, health, Long::sum);
        }

        void appendChildIfNotExists(char childChar) {
            if (!hasChild(childChar)) {
                this.childs.put(childChar, new Vertex());
            }
        }

        boolean hasChild(char childChar) {
            return this.childs.containsKey(childChar);
        }

        Vertex getChild(char childChar) {
            return this.childs.get(childChar);
        }

    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

//        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Step\\IdeaProjects\\Hackerrank\\src\\input13.txt"), 10000000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1000000);

        final int numberOfGenes = readInt(reader);
        final char[][] genes = new char[numberOfGenes][];

        for (int i = 0; i < numberOfGenes; i++) {
            genes[i] = readString(reader);
        }

        NavigableSet<Integer> groups = new TreeSet<>();
        for (int i = 0; i < numberOfGenes; i++) {
            groups.add(genes[i].length);
        }

        final long[] healths = new long[genes.length];
        for (int i = 0; i < healths.length; i++) {
            healths[i] = readInt(reader);
        }
        final int numberOfDnaStrands = readInt(reader);

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;

        int[] dnaStrandStartIndexes = new int[numberOfDnaStrands];
        int[] dnaStrandEndIndexes = new int[numberOfDnaStrands];
        char[][] dnaStrands = new char[numberOfDnaStrands][];

        for (int i = 0; i < numberOfDnaStrands; i++) {
            dnaStrandStartIndexes[i] = readInt(reader);
            dnaStrandEndIndexes[i] = readInt(reader);
            dnaStrands[i] = readString(reader);
        }

        reader.close();

        if (groups.size() <= 1) {
            Map<String, NavigableMap<Integer, Long>> healthsMap = new HashMap<>();

            for (int i = 0; i < numberOfGenes; i++) {
                NavigableMap<Integer, Long> healthsIndexMap = healthsMap.get(new String(genes[i]));
                if (healthsIndexMap == null) {
                    healthsIndexMap = new TreeMap<>();
                }
                healthsIndexMap.put(i, healths[i]);
                healthsMap.put(new String(genes[i]), healthsIndexMap);
            }

            for (int i = 0; i < numberOfDnaStrands; i++) {
                int dnaStrandStartIndex = dnaStrandStartIndexes[i];
                int dnaStrandEndIndex = dnaStrandEndIndexes[i];
                char[] dnaStrand = dnaStrands[i];
                long currentHealth = 0;

                for (Integer group : groups) {
                    for (int j = 0; j <= dnaStrand.length - group; j++) {
                        String substring = new String(Arrays.copyOfRange(dnaStrand, j, j + group));
                        NavigableMap<Integer, Long> healthsIndexMap = healthsMap.get(substring);
                        if (healthsIndexMap != null) {
                            NavigableMap<Integer, Long> subMap = healthsIndexMap.subMap(dnaStrandStartIndex, true, dnaStrandEndIndex, true);
                            for (Long value : subMap.values()) {
                                currentHealth += value;
                            }
                        }
                    }
                }

                if (currentHealth > max) {
                    max = currentHealth;
                }
                if (currentHealth < min) {
                    min = currentHealth;
                }
            }
        } else {
            int maxStart = Integer.MIN_VALUE;
            int minEnd = Integer.MAX_VALUE;
            for (int i = 0; i < numberOfDnaStrands; i++) {
                int dnaStrandStartIndex = dnaStrandStartIndexes[i];
                int dnaStrandEndIndex = dnaStrandEndIndexes[i];
                if (dnaStrandStartIndex > maxStart) {
                    maxStart = dnaStrandStartIndex;
                }
                if (dnaStrandEndIndex < minEnd) {
                    minEnd = dnaStrandEndIndex;
                }
            }

            Trie trie = new Trie(maxStart, minEnd);
            for (int i = 0; i < numberOfGenes; i++) {
                trie.add(genes[i], healths[i], i);
            }

            for (int i = 0; i < numberOfDnaStrands; i++) {
                int dnaStrandStartIndex = dnaStrandStartIndexes[i];
                int dnaStrandEndIndex = dnaStrandEndIndexes[i];
                char[] dnaStrand = dnaStrands[i];
                long currentHealth = 0;

                Vertex currentVertex = null;
                int j = 0;
                int k = 0;

                while (k < dnaStrand.length) {
                    char currentChar = dnaStrand[k];
                    if (currentVertex == null) {
                        currentVertex = trie.childs.get(currentChar);
                    } else {
                        currentVertex = currentVertex.childs.get(currentChar);
                    }
                    if (currentVertex != null) {
                        currentHealth += currentVertex.getHealth(dnaStrandStartIndex, dnaStrandEndIndex);
                        k++;
                    } else {
                        j++;
                        k = j;
                    }
                }
                j++;
                k = j;
                currentVertex = null;

                while (j < dnaStrand.length) {
                    char currentChar = dnaStrand[k];
                    if (currentVertex == null) {
                        currentVertex = trie.childs.get(currentChar);
                    } else {
                        currentVertex = currentVertex.childs.get(currentChar);
                    }
                    if (currentVertex != null) {
                        currentHealth += currentVertex.getHealth(dnaStrandStartIndex, dnaStrandEndIndex);
                        k++;
                        if (k == dnaStrand.length) {
                            j++;
                            k = j;
                            currentVertex = null;
                        }
                    } else {
                        j++;
                        k = j;
                    }
                }

                if (currentHealth > max) {
                    max = currentHealth;
                }
                if (currentHealth < min) {
                    min = currentHealth;
                }
            }
        }

        System.out.println(min + " " + max);
//        System.out.println(System.currentTimeMillis() - start);
    }

    static int readInt(BufferedReader bufferedReader) throws IOException {
        int result = 0;
        int next = skipWhitespaces(bufferedReader);
        for (; next != ' ' && next != '\n' && next != '\r' && next != -1; next = bufferedReader.read()) {
            result = result * 10 + (next - '0');
        }
        return result;
    }

    static Set<Character> uniqueChars = new HashSet<>();

    static char[] readString(BufferedReader bufferedReader) throws IOException {
        StringBuilder stringBuffer = new StringBuilder();
        int next = skipWhitespaces(bufferedReader);
        for (; next != ' ' && next != '\n' && next != '\r' && next != -1; next = bufferedReader.read()) {
            stringBuffer.append((char) next);
            uniqueChars.add((char) next);
        }
        char[] result = new char[stringBuffer.length()];
        stringBuffer.getChars(0, stringBuffer.length(), result, 0);
        return result;
    }

    static int skipWhitespaces(BufferedReader bufferedReader) throws IOException {
        int next;
        do {
            next = bufferedReader.read();
        } while (next == ' ' || next == '\n' || next == '\r');
        return next;
    }

}
