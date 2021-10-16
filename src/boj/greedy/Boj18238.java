package boj.greedy;

import java.io.*;

public class Boj18238 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String s = reader.readLine();
            int answer = 0;
            char position = 'A';
            int count = 'Z' - 'A' + 1;

            for (char c : s.toCharArray()) {
                int l = Math.abs(c - position);
                int r = Math.abs(count - l);
                answer += Math.min(l, r);
                position = c;
            }

            writer.write(answer + "");
        }
    }

}
