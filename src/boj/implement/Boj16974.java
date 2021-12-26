package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16974 {

    public static long[] h;

    public static long[] p;

    public static int n;

    public static long x;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Long.parseLong(st.nextToken());
            h = new long[n + 1];
            p = new long[n + 1];

            h[0] = 1;
            p[0] = 1;

            for (int i = 1; i <= n; ++i) {
                h[i] = 1 + h[i - 1] + 1 + h[i - 1] + 1;
                p[i] = p[i - 1] + 1 + p[i - 1];
            }

            writer.write(solve(n, x) + "");
        }
    }

    private static long solve(int n, long x) {
        if (n == 0) {
            if (x == 0) return 0;
            else if (x == 1) return 1;
        }

        if (x == 1) {
            return 0;
        } else if (x <= 1 + h[n - 1]) {
            return solve(n - 1, x - 1);
        } else if (x == 1 + h[n - 1] + 1) {
            return p[n - 1] + 1;
        } else if (x <= 1 + h[n - 1] + 1 + h[n - 1]) {
            return p[n - 1] + 1 + solve(n - 1, x - (1 + h[n - 1] + 1));
        } else {
            return p[n - 1] + 1 + p[n - 1];
        }
    }

}