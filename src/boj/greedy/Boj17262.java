package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj17262 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int max = 0;
            int min = 100001;

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                max = Math.max(max, Integer.parseInt(stringTokenizer.nextToken()));
                min = Math.min(min, Integer.parseInt(stringTokenizer.nextToken()));
            }

            int answer = Math.max(max - min, 0);
            writer.write(answer + "");
        }
    }

}
