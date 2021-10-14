package boj.greedy;

import java.io.*;

public class Boj10162 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());
            int[] buttons = {300, 60, 10};
            int[] answers = new int[buttons.length];

            for (int i = 0; i < buttons.length; i++) {
                int m = t / buttons[i];
                answers[i] = m;
                t -= m * buttons[i];
            }

            if (t > 0) {
                writer.write("-1");
            } else {
                for (int answer : answers) {
                    writer.write(answer + " ");
                }
            }
        }
    }

}
