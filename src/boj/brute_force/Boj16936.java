package boj.brute_force;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj16936 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            long[][] arr = new long[n][2];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                arr[i][1] = Long.parseLong(stringTokenizer.nextToken());
                long value = arr[i][1];
                while (true) {
                    if (value % 3 == 0) {
                        arr[i][0] += 1;
                        value /= 3;
                    } else {
                        break;
                    }
                }
            }

            Arrays.sort(arr, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return Long.compare(o1[1], o2[1]);
                } else {
                    return Long.compare(o2[0], o1[0]);
                }
            });

            for (int i = 0; i < n; i++) {
                writer.write(arr[i][1] + " ");
            }
        }
    }

}