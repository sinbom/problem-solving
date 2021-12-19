package boj.brute_force;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Boj1759 {

    public static int l;

    public static int c;

    public static String[] answers;

    public static String[] keys;

    public static Set<String> set = Stream
            .of("a", "e", "i", "o", "u")
            .collect(Collectors.toSet());

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            l = Integer.parseInt(tokenizer.nextToken());
            c = Integer.parseInt(tokenizer.nextToken());
            keys = reader.readLine().split(" ");
            answers = new String[l];
            Arrays.sort(keys);

            go(0, 0);
        }
    }

    public static void go(int ansIndex, int keyIndex) {
        if (ansIndex >= l) {
            int count = 0;
            for (String answer : answers) {
                if (set.contains(answer)) {
                    count++;
                }
            }
            if (count >= 1 && l - count >= 2) {
                System.out.println(String.join("", answers));
            }
            return;
        }
        while (keyIndex < c) {
            answers[ansIndex] = keys[keyIndex++];
            go(ansIndex + 1, keyIndex);
        }
    }

}
