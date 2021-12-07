package com.stiplin.hackerrank.tasks.medium;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QueensAttackII {

    private static class Obstacle {
        int r_q;
        int c_q;
        int row;
        int col;

        Obstacle(int r_q, int c_q, int row, int col) {
            this.r_q = r_q;
            this.c_q = c_q;
            this.row = row;
            this.col = col;
        }

        private boolean isInRange(int n) {
            return row <= n || col <= n;
        }

        private boolean isUp() {
            return c_q == col && r_q < row;
        }

        private boolean isUpRight() {
            return row == r_q - c_q + col && row > r_q && col > c_q;
        }

        private boolean isRight() {
            return r_q == row && c_q < col;
        }

        private boolean isDownRight() {
            return row == r_q + c_q - col && row < r_q && col > c_q;
        }

        private boolean isDown() {
            return c_q == col && r_q > row;
        }

        private boolean isDownLeft() {
            return row == r_q - c_q + col && row < r_q && col < c_q;
        }

        private boolean isLeft() {
            return r_q == row && c_q > col;
        }

        private boolean isUpLeft() {
            return row == r_q + c_q - col && row > r_q && col < c_q;
        }

    }

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        if (n == 1) {
            return 0;
        }

        int basePossibleVariants = n * 2 - 2
                + Math.min(r_q - 1, c_q - 1)
                + Math.min(r_q - 1, n - c_q)
                + Math.min(n - r_q, c_q - 1)
                + Math.min(n - r_q, n - c_q);

        Obstacle upObstacle = null;
        Obstacle upRightObstacle = null;
        Obstacle rightObstacle = null;
        Obstacle downRightObstacle = null;
        Obstacle downObstacle = null;
        Obstacle downLeftObstacle = null;
        Obstacle leftObstacle = null;
        Obstacle upLeftObstacle = null;

        int result = basePossibleVariants;

        for (int i = 0; i < k; i++) {
            Obstacle obstacle = new Obstacle(r_q, c_q, obstacles[i][0], obstacles[i][1]);

            if (!obstacle.isInRange(n)) {
                continue;
            }
            if (obstacle.isUp()) {
                if (upObstacle == null || obstacle.row < upObstacle.row) {
                    upObstacle = obstacle;
                }
            } else if (obstacle.isUpRight()) {
                if (upRightObstacle == null || (obstacle.row < upRightObstacle.row && obstacle.col < upRightObstacle.col)) {
                    upRightObstacle = obstacle;
                }
            } else if (obstacle.isRight()) {
                if (rightObstacle == null || (obstacle.col < rightObstacle.col)) {
                    rightObstacle = obstacle;
                }
            } else if (obstacle.isDownRight()) {
                if (downRightObstacle == null || (obstacle.row > downRightObstacle.row && obstacle.col < downRightObstacle.col)) {
                    downRightObstacle = obstacle;
                }
            } else if (obstacle.isDown()) {
                if (downObstacle == null || (obstacle.row > downObstacle.row)) {
                    downObstacle = obstacle;
                }
            } else if (obstacle.isDownLeft()) {
                if (downLeftObstacle == null || (obstacle.row > downLeftObstacle.row && obstacle.col > downLeftObstacle.col)) {
                    downLeftObstacle = obstacle;
                }
            } else if (obstacle.isLeft()) {
                if (leftObstacle == null || (obstacle.col > leftObstacle.col)) {
                    leftObstacle = obstacle;
                }
            } else if (obstacle.isUpLeft()) {
                if (upLeftObstacle == null || (obstacle.col > upLeftObstacle.col && obstacle.row < upLeftObstacle.row)) {
                    upLeftObstacle = obstacle;
                }
            }
        }

        if (upObstacle != null) {
            result -= n - upObstacle.row + 1;
        }
        if (upRightObstacle != null) {
            result -= n + 1 - Math.max(upRightObstacle.row, upRightObstacle.col);
        }
        if (rightObstacle != null) {
            result -= n - rightObstacle.col + 1;
        }
        if (downRightObstacle != null) {
            result -= n + 1 - Math.max(n + 1 - downRightObstacle.row, downRightObstacle.col);
        }
        if (downObstacle != null) {
            result -= downObstacle.row;
        }
        if (downLeftObstacle != null) {
            result -= n + 1 - Math.max(n + 1 - downLeftObstacle.row, n + 1 - downLeftObstacle.col);
        }
        if (leftObstacle != null) {
            result -= leftObstacle.col;
        }
        if (upLeftObstacle != null) {
            result -= n + 1 - Math.max(upLeftObstacle.row, n + 1 - upLeftObstacle.col);
        }

        return result;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
