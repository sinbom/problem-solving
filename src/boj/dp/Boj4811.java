package boj.dp;

import java.io.*;

public class Boj4811 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            long[] dp = new long[31];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;

            for (int i = 3; i <= 30; i++) {
                long cnt = 0;

                for (int j = 0; j < i; j++) {
                    cnt += dp[j] * dp[i - 1 - j];
                }

                dp[i] = cnt;
            }

            StringBuilder stringBuilder = new StringBuilder();

            while (true) {
                int N = Integer.parseInt(reader.readLine());

                if (N == 0) {
                    break;
                }

                stringBuilder
                        .append(dp[N])
                        .append("\n");
            }

            writer.write(stringBuilder.toString());
        }
    }

}