package boj.greedy;

import java.io.*;

public class Boj15881 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int answer = kmp(reader.readLine(), "pPAp");

            writer.write(answer + "");
        }
    }

    public static int kmp(String target, String source) {
        int[] prefix = prefix(source);
        int targetLength = target.length();
        int sourceLength = source.length();
        int count = 0;
        int j = 0;

        for (int i = 0; i < targetLength; i++) {
            while (j > 0 && target.charAt(i) != source.charAt(j)) {
                j = prefix[j - 1];
            }
            if (target.charAt(i) == source.charAt(j)) {
                if (j == sourceLength - 1) {
                    count++;
                    j = 0;
                } else {
                    j++;
                }
            }
        }

        return count;
    }

    public static int[] prefix(String source) {
        int length = source.length();
        int[] prefix = new int[length];
        int j = 0;

        for (int i = 1; i < length; i++) {
            while (j > 0 && source.charAt(i) != source.charAt(j)) {
                j = prefix[j - 1];
            }
            if (source.charAt(i) == source.charAt(j)) {
                j++;
                prefix[i] = j;
            }
        }

        return prefix;
    }



}
