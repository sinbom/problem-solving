package boj.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj12872 {

    public static long[][] dp;

    public static int n;

    public static int m;

    public static int p;

    public static final long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            dp = new long[p + 1][n + 1];

            for (int i = 0; i <= p; i++) {
                Arrays.fill(dp[i], -1);
            }

            writer.write(go(0, 0) + "");
        }
    }

    public static long go(int idx, int x) {
        int y = n - x;

        if (idx == p) {
            if (y == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dp[idx][x] != -1) {
            return dp[idx][x];
        }

        long ans = 0;

        if (y > 0) {
            ans += go(idx + 1, x + 1) * y;
        }

        if (x > m) {
            ans += go(idx + 1, x) * (x - m);
        }

        ans %= MOD;
        dp[idx][x] = ans;

        return ans;
    }

}
