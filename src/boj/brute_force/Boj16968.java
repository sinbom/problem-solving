package boj.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj16968 {

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            char[] chars = reader.readLine().toCharArray();
            int answer = 1;

            for (int i = 0; i < chars.length; i++) {
                if (i == 0) {
                    if (chars[i] == 'c') {
                        answer *= 26;
                    } else {
                        answer *= 10;
                    }
                } else {
                    if (chars[i] == chars[i - 1]) {
                        if (chars[i] == 'c') {
                            answer *= 25;
                        } else {
                            answer *= 9;
                        }
                    } else {
                        if (chars[i] == 'c') {
                            answer *= 26;
                        } else {
                            answer *= 10;
                        }
                    }
                }
            }

            writer.write(answer + "");
        }
    }
}