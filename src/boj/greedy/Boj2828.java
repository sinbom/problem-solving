package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2828 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int j = Integer.parseInt(reader.readLine());
            int start = 1;
            int end = start + (m - 1);
            int answer = 0;

            for (int i = 0; i < j; i++) {
                int apple = Integer.parseInt(reader.readLine());
                if (start <= apple && end >= apple) {
                    continue;
                }

                int move;
                if (start < apple) {
                    move = apple - end;
                    start += move;
                    end += move;
                } else {
                    move = start - apple;
                    start -= move;
                    end -= move;
                }
                answer += move;
            }

            writer.write(answer + "");

        }
    }

}
