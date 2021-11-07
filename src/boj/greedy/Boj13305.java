package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj13305 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] d = new int[n - 1];
            int[] o = new int[n];

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < d.length; i++) {
                d[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < o.length; i++) {
                o[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            long current = o[0];
            long answer = current * d[0];

            for (int i = 1; i < o.length - 1; i++) {
                if (current > o[i]) {
                    current = o[i];
                }

                answer += current * d[i];
            }

            writer.write(answer + "");
        }
    }

}