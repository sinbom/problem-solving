package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj4796 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String line;

            for (int i = 1; !(line = reader.readLine()).equals("0 0 0"); i++) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                int l = Integer.parseInt(tokenizer.nextToken());
                int p = Integer.parseInt(tokenizer.nextToken());
                int v = Integer.parseInt(tokenizer.nextToken());
                int answer = v / p * l + Math.min(v % p, l);
                writer.write("Case " + i + ": " + answer + "\n");
            }
        }
    }

}
