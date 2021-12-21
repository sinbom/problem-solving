package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj11723 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int s = 0;
            int m = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                String operation = tokenizer.nextToken();
                if (operation.equals("add")) {
                    int n = Integer.parseInt(tokenizer.nextToken());
                    s |= 1 << (n - 1);
                } else if (operation.equals("check")) {
                    int n = Integer.parseInt(tokenizer.nextToken());
                    writer.write(((s >> (n - 1)) & 1) + "\n");
                } else if (operation.equals("remove")) {
                    int n = Integer.parseInt(tokenizer.nextToken());
                    s &= ~(1 << (n - 1));
                } else if (operation.equals("toggle")) {
                    int n = Integer.parseInt(tokenizer.nextToken());
                    s ^= 1 << (n - 1);
                } else if (operation.equals("all")){
                    s = (1 << 20) - 1;
                } else {
                    s = 0;
                }
            }
        }
    }

}