package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16958 {

    public static int n;

    public static int m;

    public static int t;

    public static int[][] city;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            t = Integer.parseInt(stringTokenizer.nextToken());
            city = new int[n + 1][3];

            for (int i = 1; i <= n; ++i) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                city[i][0] = Integer.parseInt(stringTokenizer.nextToken());
                city[i][1] = Integer.parseInt(stringTokenizer.nextToken());
                city[i][2] = Integer.parseInt(stringTokenizer.nextToken());
            }

            m = Integer.parseInt(reader.readLine());

            for (int i = 0; i < m; ++i) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                int distance = 0;

                if (city[a][0] == 1 && city[b][0] == 1) {
                    distance = getDistance(a, b);
                } else if (city[a][0] == 1) {
                    int bToTelpo = getNearestTeleport(b);
                    distance = Math.min(bToTelpo + t, getDistance(a, b));
                } else if (city[b][0] == 1) {
                    int aToTelpo = getNearestTeleport(a);
                    distance = Math.min(aToTelpo + t, getDistance(a, b));
                } else {
                    int bToTelpo = getNearestTeleport(b);
                    int aToTelpo = getNearestTeleport(a);
                    distance = Math.min(bToTelpo + aToTelpo + t, getDistance(a, b));
                }

                writer.write(distance + "\n");
            }
        }
    }

    public static int getNearestTeleport(int point) {
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= n; ++i) {
            if (city[i][0] == 0) {
                continue;
            }

            min = Math.min(min, getDistance(point, i));
        }

        return min;
    }

    public static int getDistance(int a, int b) {
        int dist = Math.abs(city[a][1] - city[b][1]) + Math.abs(city[a][2] - city[b][2]);

        if (city[a][0] == 1 && city[b][0] == 1) {
            dist = Math.min(dist, t);
        }

        return dist;
    }

}









