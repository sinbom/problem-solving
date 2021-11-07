package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1783 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int n = Integer.parseInt(stringTokenizer.nextToken());

            if (m == 1) {
                writer.write("1");
            } else if (m == 2) {
                writer.write(Math.min((n + 1) / 2, 4) + "");
            } else if (n < 7) {
                writer.write(Math.min(n, 4) + "");
            } else {
                writer.write(n - 7 + 5 + "");
            }
        }
    }

}