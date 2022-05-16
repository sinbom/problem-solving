package boj.tree;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj2015 {

    public static int n;

    public static int k;

    public static int[] data;

    public static Map<Integer, Long> map = new HashMap<>();

    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            data = new int[n + 1];

            long answer = 0;

            st = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= n; i++) {
                data[i] = Integer.parseInt(st.nextToken()) + data[i - 1];

                if (data[i] == k) {
                    answer++;
                }
                if (map.containsKey(data[i] - k)) {
                    answer += map.get(data[i] - k);
                }
                if (!map.containsKey(data[i])) {
                    map.put(data[i], 1L);
                } else {
                    map.put(data[i], map.get(data[i]) + 1);
                }
            }

            writer.write(answer + "\n");
        }
    }
}
