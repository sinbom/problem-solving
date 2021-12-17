package boj.brute_force;

import java.io.*;
import java.util.Arrays;

public class Boj1476 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int[] check = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(n -> Integer.parseInt(n) - 1)
                    .toArray();
            int[] esm = new int[3];

            for (int year = 0; ; year++) {
                esm[0] = year % 15;
                esm[1] = year % 28;
                esm[2] = year % 19;
                if (Arrays.compare(esm, check) == 0) {
                    writer.write(year + 1 + "");
                    return;
                }
            }
        }
    }

}


