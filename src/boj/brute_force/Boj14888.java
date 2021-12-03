package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14888 {

    public static int n;

    public static int[] a;

    public static int[] op = new int[4];

    public static int max = -1000000000;

    public static int min = 1000000000;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            a = new int[n];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < 4; i++) {
                op[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            go(1, a[0]);

            writer.write(max + "\n" + min);
        }
    }

    public static void go(int index, int sum) {
        if (index == n) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i]--;
                if (i == 0) {
                    go(index + 1, sum + a[index]);
                } else if (i == 1) {
                    go(index + 1, sum - a[index]);
                } else if (i == 2) {
                    go(index + 1, sum * a[index]);
                } else {
                    go(index + 1, sum / a[index]);
                }
                op[i]++;
            }
        }
    }

}