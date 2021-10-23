package boj.greedy;

import java.io.*;

public class Boj19564 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String s = reader.readLine();
            int answer = 1;
            char c = s.charAt(0);

            for (int i = 1; i < s.length(); i++) {
                char next = s.charAt(i);
                if (c >= next) {
                    answer++;
                }
                c = next;
            }

            writer.write(answer + "");
        }
    }

}
