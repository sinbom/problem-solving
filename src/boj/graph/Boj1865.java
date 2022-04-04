package boj.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1865 {

    public static int n;

    public static int m;

    public static int w;

    public static int[] dist;

    public static List<ArrayList<Road>> a;

    public static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int tc = Integer.parseInt(reader.readLine());
            StringBuilder sb = new StringBuilder();

            while (tc-- > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                n = Integer.parseInt(stringTokenizer.nextToken());
                m = Integer.parseInt(stringTokenizer.nextToken());
                w = Integer.parseInt(stringTokenizer.nextToken());
                dist = new int[n + 1];
                a = new ArrayList<>();

                for (int i = 0; i <= n; i++) {
                    a.add(new ArrayList<>());
                }

                for (int i = 0; i < m + w; i++) {
                    stringTokenizer = new StringTokenizer(reader.readLine());
                    int start = Integer.parseInt(stringTokenizer.nextToken());
                    int end = Integer.parseInt(stringTokenizer.nextToken());
                    int weight = Integer.parseInt(stringTokenizer.nextToken());

                    if (i < m) {
                        a.get(start).add(new Road(end, weight));
                        a.get(end).add(new Road(start, weight));
                    } else {
                        a.get(start).add(new Road(end, -weight));
                    }
                }

                boolean isMinusCycle = false;

                for (int i = 1; i <= n; i++) {
                    if (bellmanFord(i)) {
                        isMinusCycle = true;
                        sb.append("YES\n");
                        break;
                    }
                }

                if (!isMinusCycle) {
                    sb.append("NO\n");
                }
            }

            writer.write(sb.toString());
        }
    }

    public static boolean bellmanFord(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean update = false;

        for (int i = 1; i < n; i++) {
            update = false;

            for (int j = 1; j <= n; j++) {
                for (Road road : a.get(j)) {
                    if (dist[j] != INF && dist[road.end] > dist[j] + road.weight) {
                        dist[road.end] = dist[j] + road.weight;
                        update = true;
                    }
                }
            }

            if (!update) {
                break;
            }
        }

        if (update) {
            for (int i = 1; i <= n; i++) {
                for (Road road : a.get(i)) {
                    if (dist[i] != INF && dist[road.end] > dist[i] + road.weight) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static class Road {

        private final int end;

        private final int weight;

        Road(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

}

