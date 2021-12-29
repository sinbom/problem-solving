package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16922 {

    public static int[] num = {1, 5, 10, 50};

    public static int[] sum = new int[10000];

    public static int n;

    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            go(0, 0, 0);

            writer.write(answer + "");
        }
    }

    public static void go(int cnt, int idx, int s) {
        if (cnt == n) {
            if (sum[s] == 0) {
                sum[s] = 1;
                answer++;
            }
            return;
        }

        for (int i = idx; i < 4; i++) {
            go(cnt + 1, i, s + num[i]);
        }
    }
}