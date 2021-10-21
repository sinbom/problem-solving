package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj17224 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int l = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int answer = 0;
            int count = 0;
            int count2 = 0;

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int value = Integer.parseInt(stringTokenizer.nextToken());
                if (l >= value) {
                    int value2 = Integer.parseInt(stringTokenizer.nextToken());
                    if (l >= value2) {
                        count2++;
                    } else {
                        count++;
                    }
                }
            }

            if (count2 >= k) {
                answer += 140 * k;
            } else {
                answer += 140 * count2;
                if (count >= k - count2) {
                    answer += 100 * (k - count2);
                } else {
                    answer += 100 * count;
                }
            }

            writer.write(answer + "");
        }
    }

}
