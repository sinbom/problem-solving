package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14471 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int[] cards = new int[m];

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                cards[i] = n - Integer.parseInt(stringTokenizer.nextToken());
            }
            Arrays.sort(cards);

            int answer = 0;
            for (int i = 0; i < m - 1; i++) {
                if (cards[i] > 0) {
                    answer += cards[i];
                }
            }

            writer.write(answer + "");
        }
    }

}
