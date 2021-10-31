package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11256 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());

            for (int i = 0; i < t; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int j = Integer.parseInt(stringTokenizer.nextToken());
                int n = Integer.parseInt(stringTokenizer.nextToken());
                int[] candies = new int[n];
                int sum = 0;
                int answer = 0;

                for (int k = 0; k < n; k++) {
                    stringTokenizer = new StringTokenizer(reader.readLine());
                    candies[k] = Integer.parseInt(stringTokenizer.nextToken()) *
                            Integer.parseInt(stringTokenizer.nextToken());
                }
                Arrays.sort(candies);

                for (int k = n - 1; k > -1 ; k--) {
                    sum += candies[k];
                    answer++;
                    if (sum >= j) {
                        break;
                    }
                }

                writer.write(answer + "\n");
            }

        }
    }

}
