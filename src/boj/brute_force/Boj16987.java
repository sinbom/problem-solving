package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj16987 {

    public static Egg[] eggs;

    public static int n;

    public static int cnt;

    public static int max;

    public static boolean flag = false;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            eggs = new Egg[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                eggs[i] = new Egg(
                        Integer.parseInt(stringTokenizer.nextToken()),
                        Integer.parseInt(stringTokenizer.nextToken())
                );
            }

            go(0);
            writer.write(max + "");
        }
    }

    public static void go(int idx) {
        if (idx == n) {
            max = Math.max(cnt, max);

            if (max == n) {
                flag = true;
            }

            return;
        }

        if (flag) {
            return;
        }

        if (eggs[idx].x <= 0) {
            go(idx + 1);
            return;
        }

        int broken = 0;

        for (int i = 0; i < n; i++) {
            if (i == idx) {
                continue;
            }

            if (eggs[i].x <= 0) {
                broken++;
                continue;
            }

            eggs[idx].x -= eggs[i].y;
            eggs[i].x -= eggs[idx].y;

            if (eggs[idx].x <= 0) {
                cnt++;
            }

            if (eggs[i].x <= 0) {
                cnt++;
            }

            go(idx + 1);

            if (eggs[idx].x <= 0) {
                cnt--;
            }

            if (eggs[i].x <= 0) {
                cnt--;
            }

            eggs[idx].x += eggs[i].y;
            eggs[i].x += eggs[idx].y;

        }
        if (broken == n - 1) {
            go(idx + 1);
        }

    }

    public static class Egg {
        private int x;
        private final int y;

        public Egg(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}






