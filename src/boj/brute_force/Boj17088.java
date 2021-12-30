package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj17088 {

    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] b = new int[n];
            int[] diff = new int[n - 1];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            if (b.length == 1) {
                writer.write("0");
                return;
            }

            for (int i = 0; i < n - 1; i++) {
                diff[i] = Math.abs(b[i] - b[i + 1]);
                if (i > 0 && Math.abs(diff[i - 1] - diff[i]) > 4) {
                    writer.write("-1");
                    return;
                }
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int cnt = 0;
                    int[] temp = b.clone();

                    temp[0] += i;
                    temp[1] += j;

                    if (i != 0) {
                        cnt += 1;
                    }

                    if (j != 0) {
                        cnt += 1;
                    }

                    int ans = check(temp, temp[1] - temp[0], 0);

                    if (ans == -1) {
                        continue;
                    }

                    ans += cnt;
                    min = Math.min(min, ans);
                }
            }

            writer.write(min + "");
        }
    }

    public static int check(int[] b, int d, int cnt) {
        for (int i = 1; i < b.length - 1; i++) {
            int diff = b[i + 1] - b[i];

            if (diff != d) {
                if (Math.abs(diff - d) > 1) {
                    return -1;
                }
                b[i + 1] += (d - diff);
                cnt += 1;
            }
        }

        return cnt;
    }
}