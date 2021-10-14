package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11034 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                int c = Integer.parseInt(tokenizer.nextToken());

                int max = Math.max(b - a, c - b);
                writer.write(max - 1 + "");
                writer.newLine();
            }

        }
    }

}
