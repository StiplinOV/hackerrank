package com.stiplin.hackerrank.tasks.medium;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XorSequence {

    // Complete the xorSequence function below.
    static long xorSequence(long lParam, long rParam) {
        long l = lParam;
        long r = rParam;
        long ll = 0;
        long lr = 0;
        long rl = 0;
        long rr = 0;
        long leftOneIndex = 0;
        long rightOneIndex = 0;
        if (l % 4 == 1) {
            ll = l;
            lr = l;
            leftOneIndex = l + 4;
            l = l + 1;
        }
        if (l % 4 == 2) {
            leftOneIndex = l + 3;
        }
        if (l % 4 == 3) {
            ll = l;
            lr = l + 2;
            leftOneIndex = l + 6;
            l = l + 3;
        }
        if (l % 4 == 0) {
            ll = l;
            lr = l + 1;
            leftOneIndex = l + 5;
            l = l + 2;
        }
        if (r % 4 == 1) {
            rl = r;
            rr = r;
            rightOneIndex = r - 4;
            r = r - 1;
        }
        if (r % 4 == 2) {
            rl = r - 1;
            rr = r;
            rightOneIndex = r - 5;
            r = r - 2;
        }
        if (r % 4 == 3) {
            rl = r - 2;
            rr = r;
            rightOneIndex = r - 6;
            r = r - 3;
        }
        if (r % 4 == 0) {
            rightOneIndex = r - 3;
        }

        long result = xor((l + 2) / 4, (r) / 4);

        if (leftOneIndex <= rightOneIndex) {
            if (((rightOneIndex - leftOneIndex) / 4) % 2 == 0) {
                result = result ^ 1;
            }
        }

        if (lr > rParam) {
            lr = rParam;
        }
        if (rl < lParam) {
            rl = 0;
            rr = 0;
        }
        if (lr == rl) {
            lr = rr;
            rl = 0;
            rr = 0;
        }
        if (ll > 0 && lr > 0 && ll <= lr) {
            for (long i = ll; i <= lr; i++) {
                if (i % 4 == 0) {
                    result = result ^ i;
                }
                if (i % 4 == 1) {
                    result = result ^ 1;
                }
                if (i % 4 == 2) {
                    result = result ^ (i + 1);
                }
            }
        }
        if (rl > 0 && rr > 0 && rl <= rr) {
            for (long i = rl; i <= rr; i++) {
                if (i % 4 == 0) {
                    result = result ^ i;
                }
                if (i % 4 == 1) {
                    result = result ^ 1;
                }
                if (i % 4 == 2) {
                    result = result ^ (i + 1);
                }
            }
        }
        return result;
    }

    static class MaxPowLR {

        long maxPow;
        long l;
        long r;

        MaxPowLR(long maxPow, long l, long r) {
            this.maxPow = maxPow;
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "MaxPowLR{" +
                    "maxPow=" + maxPow +
                    ", l=" + l +
                    ", r=" + r +
                    '}';
        }
    }

    static long xor(long l, long r) {
        if (l == 0) {
            l = 1;
        }
        if (l > r) {
            return 0;
        }
        MaxPowLR maxPower = maxPow(l, r);
        long left = maxPower.l;
        long right = maxPower.r;
        long maxPow = maxPower.maxPow;

        if (maxPow - left < right - maxPow) {
            left = maxPow + (maxPow - left);
            return (maxPow * 8 - 1) ^ xor(left + 1, right);
        }
        if (maxPow - left > right - maxPow) {
            right = maxPow - (right - maxPow);
            return (maxPow * 8 - 1) ^ xor(left, right - 1);
        }
        return maxPow * 8 - 1;
    }

    static MaxPowLR maxPow(long l, long r) {
        long maxPow = getMax2Pow(r);
        if (maxPow >= l) {
            return new MaxPowLR(maxPow, l, r);
        } else {

            return maxPow(maxPow - (Math.abs(maxPow - r)), maxPow - (Math.abs(maxPow - l)));
        }
    }

    static long getMax2Pow(long number) {
        long result = 1;
        while (number - result >= result) {
            result = result * 2;
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] lr = scanner.nextLine().split(" ");

            long l = Long.parseLong(lr[0]);

            long r = Long.parseLong(lr[1]);

            long result = xorSequence(l, r);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
