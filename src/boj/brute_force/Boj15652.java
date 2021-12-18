package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj15652 {

    public static int[] cnt;

    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            cnt = new int[n + 1];
            go(n, m, 1, 0);
            writer.flush();
            writer.close();
        }
    }

    public static void go(int n, int m, int index, int selected) throws IOException {
        if (selected == m) {
            for (int i = 0; i < cnt.length; i++) {
                for (int j = 0; j < cnt[i]; j++) {
                    writer.write(i + "");
                    if (j + 1 != cnt[i]) {
                        writer.write(" ");
                    } else {
                        writer.newLine();
                    }
                }
            }
        } else {
            if (index > n) {
                return;
            }
            for (int i = m - selected; i >= 1; i--) {
                cnt[index] = i;
                go(n, m, index + 1, selected + i);
            }
            cnt[index] = 0;
            go(n, m, index + 1, selected);
        }

    }

}