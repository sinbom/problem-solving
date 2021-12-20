package boj.brute_force;

import java.io.*;

public class Boj1248 {

    public static int[] a;

    public static char[][] p;

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(reader.readLine());
            a = new int[n];
            p = new char[n][n];
            String s = reader.readLine();
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    p[i][j] = s.charAt(i * n + j - (i + 1) * i / 2);
                }
            }
            go(n, 0);
        }
    }


    public static boolean go(int n, int selected) {
        if (selected == n) {
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i] + " ");
            }
            return true;
        }
        int start;
        int end;
        if (p[selected][selected] == '+') {
            start = 1;
            end = 10;
        } else if (p[selected][selected] == '-') {
            start = -10;
            end = -1;
        } else {
            start = end = 0;
        }

        outer: for (int i = start; i <= end; i++) {
            a[selected] = i;
            int sum = 0;
            for (int j = selected; j >= 0; j--) {
                sum += a[j];
                if (p[j][selected] == '+') {
                    if (sum < 1) {
                        continue outer;
                    }
                } else if (p[j][selected] == '-') {
                    if (sum > -1) {
                        continue outer;
                    }
                } else {
                    if (sum != 0) {
                        continue outer;
                    }
                }
            }
            if (go(n, selected + 1)) {
                return true;
            }
        }

        return false;
    }

}