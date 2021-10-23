package boj.greedy;

import java.io.*;

public class Boj1439 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String s = reader.readLine();
            int answer = 0;
            char c = s.charAt(0);

            for (int i = 1; i < s.length(); i++) {
                char next = s.charAt(i);
                if (c != next) {
                    answer++;
                }
                c = next;
            }

            answer = (answer + 1) / 2;
            writer.write(answer + "");
        }
    }

}
