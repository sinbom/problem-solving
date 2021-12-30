package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj16938 {

    public static int[] number;

    public static int n;

    public static int l;

    public static int r;

    public static int x;

    public static int answer = 0;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            l = Integer.parseInt(stringTokenizer.nextToken());
            r = Integer.parseInt(stringTokenizer.nextToken());
            x = Integer.parseInt(stringTokenizer.nextToken());
            number = new int[n];

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                number[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            go(0, -1, 1000001, 0, 0);

            writer.write(answer + "");
        }
    }

    public static void go(int idx, int max, int min, int sum, int cnt) {
        if (cnt >= 2) {
            if (sum >= l && sum <= r && max - min >= x) {
                answer++;
            }
        }

        if (idx == n) {
            return;
        }

        for (int i = idx; i < n; i++) {
            go(
                    i + 1,
                    Math.max(max, number[i]),
                    Math.min(number[i], min), sum + number[i],
                    cnt + 1
            );
        }
    }
}