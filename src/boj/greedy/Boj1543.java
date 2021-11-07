package boj.greedy;

import java.io.*;

public class Boj1543 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String target = reader.readLine();
            String pattern = reader.readLine();
            int answer = kmp(target, pattern);

            writer.write(answer + "");
        }
    }

    public static int kmp(String target, String pattern) {
        int count = 0;
        int[] prefix = prefix(pattern);
        int j = 0;

        for (int i = 0; i < target.length(); i++) {
            while (j > 0 && target.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (target.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    count++;
                    j = 0;
                } else {
                    j++;
                }
            }
        }

        return count;
    }

    public static int[] prefix(String pattern) {
        int[] prefix = new int[pattern.length()];
        int j = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                prefix[i] = j;
            }
        }

        return prefix;
    }

}