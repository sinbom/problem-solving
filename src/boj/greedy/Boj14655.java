package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14655 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int sum = 0;

            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            StringTokenizer stringTokenizer2 = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                int r1 = Integer.parseInt(stringTokenizer.nextToken());
                int r2 = Integer.parseInt(stringTokenizer2.nextToken());
                sum += Math.abs(r1) + Math.abs(r2);
            }

            writer.write(sum + "");

        }
    }

}
