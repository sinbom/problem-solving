package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj6064 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int t = Integer.parseInt(reader.readLine());

            for (int i = 0; i < t; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int m = Integer.parseInt(tokenizer.nextToken());
                int n = Integer.parseInt(tokenizer.nextToken());
                int x = Integer.parseInt(tokenizer.nextToken()) - 1;
                int y = Integer.parseInt(tokenizer.nextToken()) - 1;
                boolean find = false;

                for (int j = x; j < m * n; j += m) {
                    if (j % n == y) {
                        writer.write(j + 1 + "\n");
                        find = true;
                        break;
                    }
                }

                if (!find) {
                    writer.write(-1 + "\n");
                }
            }
        }
    }

}