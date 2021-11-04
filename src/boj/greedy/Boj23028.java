package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj23028 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int[] x = new int[8 - n];
            int[] y = new int[8 - n];

            for (int i = 0; i < 10; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                if (i < 8 - n) {
                    x[i] = Integer.parseInt(stringTokenizer.nextToken());
                    y[i] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            if (a >= 66 && b >= 130) {
                writer.write("Nice");
                return;
            }

            for (int i = 0; i < 8 - n; i++) {
                a += x[i] * 3;
                b += x[i] * 3;
                int remain = 6 - x[i];
                b += remain > y[i] ? y[i] * 3 : remain * 3;
            }

            if (a >= 66 && b >= 130) {
                writer.write("Nice");
            } else {
                writer.write("Nae ga wae");
            }
        }
    }

}
