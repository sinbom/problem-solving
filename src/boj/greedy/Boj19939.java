package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj19939 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int count = k * (k + 1) / 2;

            if (count > n) {
                writer.write("-1");
            } else if ((n - count) % k == 0) {
                writer.write(k - 1 + "");
            } else {
                writer.write(k + "");
            }
        }
    }

}
