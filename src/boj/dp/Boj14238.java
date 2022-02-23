package boj.dp;

import java.io.*;

public class Boj14238 {

    public static int[][][][][] dp;

    public static boolean flag = false;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String line = reader.readLine();
            int aCnt = 0;
            int bCnt = 0;
            int cCnt = 0;

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'A')
                    aCnt++;

                else if (line.charAt(i) == 'B')
                    bCnt++;

                else
                    cCnt++;
            }

            dp = new int[aCnt + 1][bCnt + 1][cCnt + 1][3][3];

            dfs(aCnt, bCnt, cCnt, "", 0, 0);

            if (!flag) {
                writer.write("-1");
            }
        }
    }

    public static void dfs(int a, int b, int c, String s, int pre2, int pre) {
        if (flag) {
            return;
        }

        if (a == 0 && b == 0 && c == 0) {
            System.out.println(s);
            flag = true;
            return;
        }

        if (dp[a][b][c][pre2][pre] == 1) {
            return;
        }

        dp[a][b][c][pre2][pre] = 1;

        if (a > 0) {
            dfs(a - 1, b, c, s + 'A', pre, 0);
        }

        if (b > 0 && pre != 1) {
            dfs(a, b - 1, c, s + 'B', pre, 1);
        }

        if (c > 0 && pre != 2 && pre2 != 2) {
            dfs(a, b, c - 1, s + 'C', pre, 2);
        }
    }
}
