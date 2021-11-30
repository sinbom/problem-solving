package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14501 {

    public static int[] dp;

    public static int[] t;

    public static int[] p;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            dp = new int[n + 1];
            t = new int[n + 1];
            p = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                t[i] = Integer.parseInt(stringTokenizer.nextToken());
                p[i] = Integer.parseInt(stringTokenizer.nextToken());
                dp[i] = p[i];
            }

            writer.write(go(n) + "");
        }
    }

    public static int go(int n) {
        int result = 1 + t[1] <= n + 1 ? dp[1] : 0;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (j + t[j] <= i) {
                    dp[i] = Math.max(dp[i], dp[j] + p[i]);
                }
            }

            if (i + t[i] <= n + 1 && result < dp[i]) {
                result = dp[i];
            }
        }

        return result;
    }

}