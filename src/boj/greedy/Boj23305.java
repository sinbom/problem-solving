package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj23305 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] counts = new int[1000001];
            int answer = 0;

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                counts[Integer.parseInt(stringTokenizer.nextToken())]++;
            }
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                counts[Integer.parseInt(stringTokenizer.nextToken())]--;
            }

            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > 0) {
                    answer += counts[i];
                }
            }

            writer.write(answer + "");
        }
    }

}
