package boj.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Boj5585 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] changes = {500, 100, 50, 10, 5, 1};
            int answer = 0;
            int n = 1000 - Integer.parseInt(reader.readLine());

            for (int change : changes) {
                int m = n / change;
                answer += m;
                n -= m * change;
            }

            writer.write(answer + "");
        }
    }

}
