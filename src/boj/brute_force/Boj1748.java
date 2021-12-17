package boj.brute_force;

import java.io.*;

public class Boj1748 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(reader.readLine());
            int m = 1;
            int sum = 0;

            for (int i = 1; m <= n; i++) {
                if (n >= m * 10) {
                    sum += (m * 10 - 1 - m + 1) * i;
                } else {
                    sum += (n - m + 1) * i;
                }
                m *= 10;
            }

            writer.write(sum + "");
        }
    }

}