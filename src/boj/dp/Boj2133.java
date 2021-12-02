package boj.dp;

import java.io.*;

public class Boj2133 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] dp = new int[31];

            dp[0] = 1;
            dp[2] = 3;

            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 2] * 3;
                for (int j = 4; j <= i; j += 2) { // 가로가 2 증가할 때 마다 새로운 도형 모양이 2개 추가됨 (j == i)
                    dp[i] += dp[i - j] * 2; // 가로가 2 증가할 때마다 추가된 새로운 도형 모양의 경우의 수를 계산
                }
            }

            writer.write(dp[n] + "");
        }
    }

}