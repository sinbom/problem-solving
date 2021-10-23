package boj.greedy;

import java.io.*;

public class Boj1789 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            long s = Long.parseLong(reader.readLine());
            long sum = 0;
            int answer = 0;

            for (int i = 1; sum <= s; i++) {
                sum += i;
                answer++;
            }

            writer.write(answer - 1 + "");
        }
    }

}
