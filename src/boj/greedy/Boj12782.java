package boj.greedy;

import java.io.*;

public class Boj12782 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());

            for (int i = 0; i < t; i++) {
                String[] split = reader.readLine().split(" ");
                String left = split[0];
                String right = split[1];
                int zero = 0;
                int one = 0;


                for (int j = 0; j < left.length(); j++) {
                    if (left.charAt(j) != right.charAt(j)) {
                        if (left.charAt(j) == '0') {
                            zero++;
                        } else {
                            one++;
                        }
                    }
                }

                int answer = Math.max(zero, one);

                writer.write(answer + "");
                writer.newLine();
            }
        }

    }

}
