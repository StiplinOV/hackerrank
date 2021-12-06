package com.stiplin.hackerrank.tasks.hard;

import java.io.*;

public class MakingCandies {

    /*
     * Complete the 'minimumPasses' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER m
     *  2. LONG_INTEGER w
     *  3. LONG_INTEGER p
     *  4. LONG_INTEGER n
     */
    static long machines;
    static long workers;
    static long cost;
    static long requiredNumberOfCandies;
    static long candies;
    static long result;

    static long minimumPasses(long machines, long workers, long cost, long requiredNumberOfCandies) {
        MakingCandies.machines = Math.max(machines, workers);
        MakingCandies.workers = Math.min(machines, workers);
        MakingCandies.cost = cost;
        MakingCandies.requiredNumberOfCandies = requiredNumberOfCandies;
        MakingCandies.candies = 0;
        result = 1;
        earn();
        shift();
        long resultWithoutInvest = resultWithoutInvest();
        while (candies < MakingCandies.requiredNumberOfCandies) {
            invest();
            earn();
            result++;
            if (resultWithoutInvest() > resultWithoutInvest) {
                return resultWithoutInvest;
            }
            shift();
            resultWithoutInvest = resultWithoutInvest();

        }
        return Math.min(result, resultWithoutInvest);
    }

    static void earn() {
        if (workers > Long.MAX_VALUE / machines) {
            candies = Long.MAX_VALUE;
            workers = 1;
            machines = 1;
            return;
        }
        candies += workers * machines;
    }

    static void shift() {
        if (candies == Long.MAX_VALUE) {
            return;
        }
        long possibleItems = candies / cost;
        if (possibleItems == 0) {
            long remaining = Math.min(cost - candies, requiredNumberOfCandies - candies);
            result += remaining / (workers * machines);
            candies += (remaining / (workers * machines)) * (workers * machines);
        }
    }

    static void invest() {
        long diff = machines - workers;
        long possibleItems = candies / cost;
        if (possibleItems > diff) {
            workers += diff;
            possibleItems -= diff;
        } else {
            workers += possibleItems;
            possibleItems = 0;
        }
        workers += possibleItems / 2;
        machines += possibleItems / 2 + possibleItems % 2;
        candies = candies % cost;
    }

    static long resultWithoutInvest() {
        if (workers > Long.MAX_VALUE / machines) {
            return Long.MAX_VALUE;
        }
        if (requiredNumberOfCandies <= candies) {
            return result;
        }
        long remainingCandies = requiredNumberOfCandies - candies;

        if ((result + (remainingCandies % (machines * workers))) > Long.MAX_VALUE - remainingCandies / (machines * workers)) {
            return Long.MAX_VALUE;
        }

        return result + remainingCandies / (machines * workers) + (remainingCandies % (machines * workers) > 0 ? 1 : 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        long m = Long.parseLong(firstMultipleInput[0]);

        long w = Long.parseLong(firstMultipleInput[1]);

        long p = Long.parseLong(firstMultipleInput[2]);

        long n = Long.parseLong(firstMultipleInput[3]);

        long result = MakingCandies.minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
