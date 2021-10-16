package boj.greedy;

import java.io.*;
import java.util.Arrays;

public class Boj18228 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] ices = Arrays
                    .stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int left = ices[0];
            int right = ices[ices.length - 1];
            int i = 0;

            while (i < ices.length && ices[i] != -1) {
                left = Math.min(left, ices[i]);
                i++;
            }
            i++;
            while (i < ices.length) {
                right = Math.min(right, ices[i]);
                i++;
            }

            writer.write(left + right + "");
        }
    }

}
