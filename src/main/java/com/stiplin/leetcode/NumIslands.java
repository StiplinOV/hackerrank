package com.stiplin.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class NumIslands {
    public static int numIslands(char[][] grid) {
        int width = grid.length;
        int height = grid[0].length;
        int[][] foundedIslands = new int[width][height];
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (foundedIslands[i][j] == 1) {
                    continue;
                }
                char symbol = grid[i][j];
                if (symbol == '0') {
                    continue;
                }
                res++;
                queue.add(new int[]{i, j});
                while (!queue.isEmpty()) {
                    int[] currentPair = queue.remove();
                    int x = currentPair[0];
                    int y = currentPair[1];
                    String visitedStr = "x" + x + "y" + y;
                    if (visited.contains(visitedStr)) {
                        continue;
                    }
                    visited.add(visitedStr);
                    foundedIslands[x][y] = 1;
                    if (x > 0) {
                        int newX = x - 1;
                        int newY = y;
                        if (foundedIslands[newX][newY] == 0) {
                            if (grid[newX][newY] == '1') {
                                queue.add(new int[]{newX, newY});
                            }
                        }
                    }
                    if (x < width - 1) {
                        int newX = x + 1;
                        int newY = y;
                        if (foundedIslands[newX][newY] == 0) {
                            if (grid[newX][newY] == '1') {
                                queue.add(new int[]{newX, newY});
                            }
                        }
                    }
                    if (y > 0) {
                        int newX = x;
                        int newY = y - 1;
                        if (foundedIslands[newX][newY] == 0) {
                            if (grid[newX][newY] == '1') {
                                queue.add(new int[]{newX, newY});
                            }
                        }
                    }
                    if (y < height - 1) {
                        int newX = x;
                        int newY = y + 1;
                        if (foundedIslands[newX][newY] == 0) {
                            if (grid[newX][newY] == '1') {
                                queue.add(new int[]{newX, newY});
                            }
                        }
                    }
                }
                visited.clear();
            }
        }
        return res;
    }
}
