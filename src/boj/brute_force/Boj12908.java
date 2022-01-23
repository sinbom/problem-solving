package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj12908 {

    public static int xs;

    public static int ys;

    public static int xe;

    public static int ye;

    public static Teleport[] tp = new Teleport[3];

    public static long min = Long.MAX_VALUE;

    public static int maxCount;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            xs = Integer.parseInt(stringTokenizer.nextToken());
            ys = Integer.parseInt(stringTokenizer.nextToken());
            stringTokenizer = new StringTokenizer(reader.readLine());
            xe = Integer.parseInt(stringTokenizer.nextToken());
            ye = Integer.parseInt(stringTokenizer.nextToken());

            for (int i = 0; i < 3; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int x1 = Integer.parseInt(stringTokenizer.nextToken());
                int y1 = Integer.parseInt(stringTokenizer.nextToken());
                int x2 = Integer.parseInt(stringTokenizer.nextToken());
                int y2 = Integer.parseInt(stringTokenizer.nextToken());
                tp[i] = new Teleport(x1, y1, x2, y2);
            }

            for (int i = 0; i <= 3; i++) {
                maxCount = i;
                goHome(xs, ys, 0, 0, 0);
            }

            writer.write(min + "");
        }
    }

    public static void goHome(int x, int y, int count, long distance, int flag) {
        if (min < distance)
            return;

        if (count == maxCount) {
            min = Math.min(min, distance + getDistance(x, y, xe, ye));
            return;
        }

        for (int i = 0; i < 3; i++) {
            if ((flag & (1 << i)) != 0) {
                continue;
            }
            goHome(tp[i].x2, tp[i].y2, count + 1, 10 + distance + getDistance(x, y, tp[i].x1, tp[i].y1), flag | 1 << i);
            goHome(tp[i].x1, tp[i].y1, count + 1, 10 + distance + getDistance(x, y, tp[i].x2, tp[i].y2), flag | 1 << i);
        }
    }

    public static long getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static class Teleport {

        private final int x1;

        private final int y1;

        private final int x2;

        private final int y2;

        Teleport(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}









