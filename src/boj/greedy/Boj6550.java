package boj.greedy;

import java.io.*;

public class Boj6550 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String str;

            while ((str = reader.readLine()) != null) {
                String[] split = str.split(" ");
                String s = split[0];
                String t = split[1];
                int j = 0;

                for (int i = 0; i < t.length() && j < s.length(); i++) {
                    if (t.charAt(i) == s.charAt(j)) {
                        j++;
                    }
                }
                writer.write(j == s.length() ? "Yes\n" : "No\n");
            }
        }
    }

}
