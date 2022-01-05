package boj.brute_force;

import java.io.*;

public class Boj16638 {

    public static boolean[] brakets;

    public static String[] input;

    public static int n;

    public static long answer = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            brakets = new boolean[n];
            input = reader.readLine().split("");

            go(1);
            writer.write(answer + "");
        }
    }

    public static void go(int idx) {
        if (idx >= n) {
            long result = calc(copy(input));
            answer = Math.max(result, answer);

            return;
        }

        if (idx == 1) {
            brakets[idx] = true;
            go(idx + 2);
            brakets[idx] = false;
        } else {
            if (!brakets[idx - 2]) {
                brakets[idx] = true;
                go(idx + 2);
                brakets[idx] = false;
            }
        }

        go(idx + 2);
    }

    public static long calc(char op, int num, long result) {
        if (op == '+') {
            result += num;
        } else if (op == '-') {
            result -= num;
        } else {
            result *= num;
        }

        return result;
    }

    public static long calc(String[] copy) {
        calcBrakets(copy);
        calcMulti(copy);
        return calcPlusMinus(copy);
    }

    public static long calcPlusMinus(String[] copy) {
        for (int i = 1; i < n; i += 2) {
            if (copy[i] != null && (copy[i].equals("+") || copy[i].equals("-"))) {
                int left = i - 1;
                int right = i + 1;

                while (copy[left] == null) {
                    left--;
                }
                while (copy[right] == null) {
                    right++;
                }

                copy[i] = calc(copy[i].charAt(0), Integer.parseInt(copy[right]), Long.parseLong(copy[left])) + "";
                copy[left] = null;
                copy[right] = null;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (copy[i] != null) {
                return Long.parseLong(copy[i]);
            }
        }

        return 0;
    }

    public static String[] calcMulti(String[] copy) {
        for (int i = 1; i < n; i += 2) {
            if (copy[i] != null && copy[i].charAt(0) == '*') {
                int left = i - 1;
                int right = i + 1;

                while (copy[left] == null) {
                    left--;
                }
                while (copy[right] == null) {
                    right++;
                }

                copy[i] = calc('*', Integer.parseInt(copy[right]), Long.parseLong(copy[left])) + "";
                copy[left] = null;
                copy[right] = null;
            }
        }
        return copy;
    }

    public static String[] calcBrakets(String[] copy) {
        for (int i = 1; i < n; i += 2) {
            if (brakets[i]) {
                copy[i] = calc(copy[i].charAt(0), Integer.parseInt(copy[i + 1]), Integer.parseInt(copy[i - 1])) + "";
                copy[i - 1] = null;
                copy[i + 1] = null;
            }
        }
        return copy;
    }

    public static String[] copy(String[] origin) {
        String[] result = new String[n];

        for (int i = 0; i < n; ++i) {
            result[i] = origin[i];
        }

        return result;
    }

}




