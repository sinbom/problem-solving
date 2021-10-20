package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2839 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int answer = 0;

            while (n > 0) {
                if (n % 5 == 0) {
                    answer += n / 5;
                    n = 0;
                    break;
                }
                n -= 3;
                answer++;
            }

            if (n == 0) {
                writer.write(answer + "");
            } else {
                writer.write("-1");
            }
        }
    }

}
