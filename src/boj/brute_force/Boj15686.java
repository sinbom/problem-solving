package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15686 {

    public static int n;

    public static int m;

    public static int[][] map;

    public static List<Point> person = new ArrayList<>();

    public static List<Point> chicken = new ArrayList<>();

    public static int answer = Integer.MAX_VALUE;

    public static boolean[] open;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    if (map[i][j] == 1) {
                        person.add(new Point(i, j));
                    } else if (map[i][j] == 2) {
                        chicken.add(new Point(i, j));
                    }
                }
            }

            open = new boolean[chicken.size()];
            go(0, 0);

            writer.write(answer + "\n");
        }
    }

    public static void go(int start, int cnt) {
        if (cnt == m) {
            int result = 0;

            for (Point point : person) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if (open[j]) {
                        int distance = Math.abs(point.x - chicken.get(j).x) + Math.abs(point.y - chicken.get(j).y);
                        temp = Math.min(temp, distance);
                    }
                }
                result += temp;
            }
            answer = Math.min(answer, result);

            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            open[i] = true;
            go(i + 1, cnt + 1);
            open[i] = false;
        }
    }

    public static class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
