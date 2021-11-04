package boj.greedy;

import java.io.*;

public class Boj21557 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());

            String[] s = reader.readLine().split(" ");
            int left = Integer.parseInt(s[0]);
            int right = Integer.parseInt(s[s.length - 1]);

            for (int i = n - 2; i > 0; i--) {
                if (i == 1) {
                    left--;
                    right--;
                } else {
                    if (left > right) {
                        left--;
                    } else {
                        right--;
                    }
                }
            }

            writer.write(Math.max(left, right) + "");
        }
    }

}
