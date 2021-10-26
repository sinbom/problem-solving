package boj.greedy;

import java.io.*;
import java.util.Arrays;

public class Boj9237 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String n = reader.readLine();
            int answer = 0;
            int sum = 0;
            int[] t = Arrays
                    .stream(
                            reader
                                    .readLine()
                                    .split(" ")
                    )
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

            for (int i = t.length - 1; i >= 0; i--) {
                int time = sum + t[i] + 1;
                answer = Math.max(answer, time);
                sum += 1;
            }

            writer.write(answer + 1 + "");
        }
    }
}
