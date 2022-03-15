package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1959 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            long m = Long.parseLong(stringTokenizer.nextToken());
            long n = Long.parseLong(stringTokenizer.nextToken());
            StringBuilder stringBuilder = new StringBuilder();

            if (m > n) {
                stringBuilder.append((n - 1) * 2 + 1).append("\n");
            } else {
                stringBuilder.append((m - 1) * 2).append("\n");
            }

            if (m == n) {
                if (m % 2 == 1) {
                    stringBuilder.append(m / 2 + 1).append(" ").append(n / 2 + 1).append("\n");
                } else {
                    stringBuilder.append(m / 2 + 1).append(" ").append(n / 2).append("\n");
                }
            } else if (m > n) {
                if (n % 2 == 0) {
                    stringBuilder.append(n / 2 + 1).append(" ").append(n / 2).append("\n");
                } else {
                    stringBuilder.append(n / 2 + 1 + (m - n)).append(" ").append(n / 2 + 1).append("\n");
                }
            } else {
                if (m % 2 == 0) {
                    stringBuilder.append(m / 2 + 1).append(" ").append(m / 2).append("\n");
                } else {
                    stringBuilder.append(m / 2 + 1).append(" ").append(m / 2 + 1 + (n - m)).append("\n");
                }
            }

            writer.write(stringBuilder.toString());
        }
    }
}
