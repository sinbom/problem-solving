package boj.dp;

import java.io.*;

public class Boj9252 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String s1 = reader.readLine();
            String s2 = reader.readLine();
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];

            for (int i = 1; i <= s1.length(); i++) {
                for (int j = 1; j <= s2.length(); j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            int i = s1.length();
            int j = s2.length();

            StringBuilder stringBuilder = new StringBuilder();

            while (i > 0 && j > 0) {
                if (dp[i][j] == dp[i - 1][j]) {
                    i--;
                } else if (dp[i][j] == dp[i][j - 1]) {
                    j--;
                } else {
                    i--;
                    j--;
                    stringBuilder.append(s1.charAt(i));
                }
            }
            writer.write(dp[s1.length()][s2.length()] + "\n");
            if (stringBuilder.length() > 0) {
                writer.write(stringBuilder.reverse().toString());
            }
        }
    }

}