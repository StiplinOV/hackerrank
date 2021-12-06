package com.stiplin.hackerrank.tasks.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MatrixLayerRotation {

    private static enum Direction {
        LEFT, TOP, RIGHT, BOTTOM
    }

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r) {
        int rowCount = matrix.size();
        int colCount = matrix.get(0).size();

        for (int row = 0; row < rowCount; row++) {
            List<Integer> rowList = matrix.get(row);
            for (int col = 0; col < colCount; col++) {
                int layer = getLayer(row, col, rowCount, colCount);
                int minRow = layer;
                int maxRow = rowCount - layer - 1;
                int minCol = layer;
                int maxCol = colCount - layer - 1;
                int layerRowSize = maxRow - minRow + 1;
                int layerColSize = maxCol - minCol + 1;
                int layerSize = layerRowSize * 2 + layerColSize * 2 - 4;
                int remainder = r % layerSize;

                Direction direction;
                if (row == minRow) {
                    direction = Direction.TOP;
                } else if (row == maxRow) {
                    direction = Direction.BOTTOM;
                } else if (col == minCol) {
                    direction = Direction.LEFT;
                } else {
                    direction = Direction.RIGHT;
                }

                int newRow = row;
                int newCol = col;

                while (remainder > 0) {
                    switch (direction) {
                        case LEFT:
                            if (newRow > minRow) {
                                remainder -= newRow - minRow;
                                newRow = minRow;
                            }
                            break;
                        case TOP:
                            if (newCol < maxCol) {
                                remainder -= maxCol - newCol;
                                newCol = maxCol;
                            }
                            break;
                        case RIGHT:
                            if (newRow < maxRow) {
                                remainder -= maxRow - newRow;
                                newRow = maxRow;
                            }
                            break;
                        default:
                            if (newCol > minCol) {
                                remainder -= newCol - minCol;
                                newCol = minCol;
                            }
                            break;
                    }
                    if (remainder > 0) {
                        direction = getNextDirection(direction);
                    }
                }

                if (remainder < 0) {
                    remainder = remainder * -1;
                    switch (direction) {
                        case LEFT:
                            newRow += remainder;
                            break;
                        case TOP:
                            newCol -= remainder;
                            break;
                        case RIGHT:
                            newRow -= remainder;
                            break;
                        default:
                            newCol += remainder;
                            break;
                    }
                }

                System.out.print(matrix.get(newRow).get(newCol) + " ");
            }
            System.out.println();
        }
    }

    private static Direction getNextDirection(Direction direction) {
        switch (direction) {
            case LEFT:
                return Direction.TOP;
            case TOP:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.BOTTOM;
            default:
                return Direction.LEFT;
        }
    }

    private static int getLayer(int row, int col, int rowCount, int colCount) {
        return Math.min(getAxisLayer(row, rowCount), getAxisLayer(col, colCount));
    }

    private static int getAxisLayer(int index, int size) {
        if (index < size / 2) {
            return index;
        }

        return size - index - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
