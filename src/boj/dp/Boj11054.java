package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11054 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] ldp = new int[1001];
            int[] rdp = new int[1001];
            int[] numbers = new int[1001];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i <= n; i++) {
                numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
                ldp[i] = 1;
                rdp[i] = 1;
            }


            for (int i = 2; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    if (numbers[j] < numbers[i]) {
                        ldp[i] = Math.max(ldp[i], ldp[j] + 1);
                    }
                }
                int ri = n + 1 - i;
                for (int j = n; j > ri; j--) {
                    if (numbers[ri] > numbers[j]) {
                        rdp[ri] = Math.max(rdp[ri], rdp[j] + 1);
                    }
                }
            }

            int answer = 0;

            for (int i = 1; i <= n; i++) {
                answer = Math.max(answer, ldp[i] + rdp[i] - 1);
            }

            writer.write(answer + "");
        }
    }

}