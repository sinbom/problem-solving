package boj.dp;

import java.io.*;

public class Boj15990 {

    public static int[][] dp = new int[100001][4];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());

            go();

            while (t-- > 0) {
                int number = Integer.parseInt(reader.readLine());
                int result = 0;

                for (int i = 1; i < 4; i++) {
                    result += dp[number][i];
                    result %= 1000000009;
                }

                writer.write(result + "\n");
            }
        }
    }

    public static void go() {
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i < 100001; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1000000009;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1000000009;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1000000009;
        }
    }

}