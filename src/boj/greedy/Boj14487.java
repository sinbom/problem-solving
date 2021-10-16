package boj.greedy;

import java.io.*;
import java.util.Arrays;

public class Boj14487 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] d = Arrays
                    .stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int answer = 0;
            for (int i = 0; i < d.length; i++) {
                int count = 0;
                for (int j = i + 1; j < d.length && d[i] > d[j]; j++) {
                    count++;
                }

                answer = Math.max(answer, count);
            }

            writer.write(answer + "");
        }
    }

}
