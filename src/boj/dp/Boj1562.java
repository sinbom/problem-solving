package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj1562 {

    private static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            long[][][] dp = new long[n + 1][11][1 << 10];

            for (int i = 1; i < 10; i++) {
                dp[1][i][1 << i] = 1;
            }

            for (int i = 2; i < n + 1; i++) {
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < 1024; k++) {
                        int bit = k | (1 << j);

                        if (j == 0) {
                            dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k]) % MOD;
                        } else if (j == 9) {
                            dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k]) % MOD;
                        } else {
                            dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k] + dp[i - 1][j - 1][k]) % MOD;
                        }
                    }
                }
            }

            long sum = 0;

            for (int i = 0; i < 10; i++) {
                sum = (sum + dp[n][i][1023]) % MOD;
            }

            writer.write(sum + "");
        }
    }

}
