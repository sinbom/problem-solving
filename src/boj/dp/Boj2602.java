package boj.dp;

import java.io.*;

public class Boj2602 {

    public static String[] str;

    public static String[] dev;

    public static String[] ang;

    public static int[][][] dp;

    public static int len;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            str = reader.readLine().split("");
            dev = reader.readLine().split("");
            ang = reader.readLine().split("");
            len = dev.length;
            dp = new int[str.length + 1][len + 1][2];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < len; j++) {
                    for (int k = 0; k < str.length; k++) {
                        dp[k][j][i] = -1;
                    }
                }
            }

            int dev_start = dfs(0, 0, 0);
            int ang_start = dfs(0, 0, 1);

            writer.write(dev_start + ang_start + "");
        }
    }

    static int dfs(int pos, int idx, int turn) {
        if (pos == str.length) {
            return 1;
        }

        if (dp[pos][idx][turn] != -1) {
            return dp[pos][idx][turn];
        }

        int count = 0;

        if (turn == 0) {
            for (int i = idx; i < len; i++) {
                if (str[pos].equals(dev[i])) {
                    count += dfs(pos + 1, i + 1, 1);
                }
            }
        } else {
            for (int i = idx; i < len; i++) {
                if (str[pos].equals(ang[i])) {
                    count += dfs(pos + 1, i + 1, 0);
                }
            }
        }

        return dp[pos][idx][turn] = count;
    }
}
