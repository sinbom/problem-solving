package boj.brute_force;

import java.io.*;
import java.util.Arrays;

public class Boj2309 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = 9;
            int[] heights = new int[n];
            int sum = 0;

            for (int i = 0; i < n; i++) {
                heights[i] = Integer.parseInt(reader.readLine());
                sum += heights[i];
            }

            Arrays.sort(heights);

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (sum - heights[i] - heights[j] == 100) {
                        for (int k = 0; k < n; k++) {
                            if (i == k || j == k) {
                                continue;
                            }
                            writer.write(heights[k] + "\n");
                        }
                        return;
                    }
                }
            }

        }
    }

}


