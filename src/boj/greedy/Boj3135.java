package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj3135 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int n = Integer.parseInt(reader.readLine());
            int count = Math.abs(a - b);

            for (int i = 0; i < n; i++) {
                int number = Integer.parseInt(reader.readLine());
                count = Math.min(count, Math.abs(b - number) + 1);
            }

            writer.write(count + "");
        }
    }

}