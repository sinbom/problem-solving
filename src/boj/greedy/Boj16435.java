package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj16435 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int l = Integer.parseInt(stringTokenizer.nextToken());
            int[] h = Arrays.stream(
                            reader
                                    .readLine()
                                    .split(" ")
                    )
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

            for (int i = 0; i < h.length; i++) {
                if (l >= h[i]) {
                    l++;
                } else {
                    break;
                }
            }

            writer.write(l + "");
        }
    }

}
