package boj.brute_force;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj2143 {

    public static int[] a;

    public static int[] b;

    public static int t;

    public static Map<Integer, Long> map = new HashMap<>();

    public static long answer = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            t = Integer.parseInt(reader.readLine());
            int n = Integer.parseInt(reader.readLine());
            a = new int[n + 1];

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            int m = Integer.parseInt(reader.readLine());
            b = new int[m];

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i; j < n; j++) {
                    sum += a[j];
                    map.put(sum, map.getOrDefault(sum, 0L) + 1L);
                }
            }

            for (int i = 0; i < m; i++) {
                int sum = 0;
                for (int j = i; j < m; j++) {
                    sum += b[j];
                    answer += map.getOrDefault(t - sum, 0L);
                }
            }

            writer.write(answer + "");
        }
    }

}