package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj16953 {

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            long a = Long.parseLong(stringTokenizer.nextToken());
            long b = Long.parseLong(stringTokenizer.nextToken());
            int answer = 1;

            while (b != a) {
                if (b < a) {
                    answer = -1;
                    break;
                }

                String str = String.valueOf(b);

                if (str.charAt(str.length() - 1) != '1' && b % 2 != 0) {
                    answer = -1;
                    break;
                }

                if (b % 2 == 0) {
                    b >>= 1;
                } else {
                    str = str.substring(0, str.length() - 1);
                    b = Long.parseLong(str);
                }

                answer++;
            }

            writer.write(answer + "\n");
        }
    }

}






