package boj.greedy;

import java.io.*;

public class Boj2720 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] changes = {25, 10, 5, 1};
            int t = Integer.parseInt(reader.readLine());

            for (int i = 0; i < t; i++) {
                int[] answers = new int[changes.length];
                int c = Integer.parseInt(reader.readLine());
                for (int j = 0; j < changes.length; j++) {
                    answers[j] = c / changes[j];
                    c -= answers[j] * changes[j];
                }

                for (int answer : answers) {
                    writer.write(answer + " ");
                }
                writer.newLine();
            }
        }
    }

}
