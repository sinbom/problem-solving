package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj1947 {

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int m = 1;
            long result = 0;
            int size = Math.min(45, n);

            for (int i = 0; i < size - 1; i++) {
                if ((n + i) % 2 == 0) {
                    result = (result + m) % 1000000000;
                } else {
                    result = (result - m) % 1000000000;
                }

                m = (m * (n - i)) % 1000000000;
            }

            writer.write((result + 1000000000) % 1000000000 + "");
        }
    }

}