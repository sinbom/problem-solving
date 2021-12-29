package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj16917 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            int count = 0;

            if ((a + b) <= c * 2) {
                count += (a * x) + (b * y);
            } else {
                if (x > y) {
                    count += y * (c * 2);
                    count += Math.min((x - y) * a, (x - y) * (c * 2));
                } else {
                    count += x * (c * 2);
                    count += Math.min((y - x) * b, (y - x) * (c * 2));
                }

            }

            writer.write(count + "");
        }
    }
}