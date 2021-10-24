package boj.greedy;

import java.io.*;

public class Boj15904 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            char[] pattern = {'U', 'C', 'P', 'C'};
            String s = reader.readLine();
            int count = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (pattern[count] == c) {
                    count++;
                    if (count == pattern.length) {
                        break;
                    }
                }
            }

            String result = count == pattern.length ? "I love UCPC" : "I hate UCPC";
            writer.write(result);
        }
    }

}
