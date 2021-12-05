package boj.greedy;

import java.io.*;
import java.util.Arrays;

public class Boj1339 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int[] arr = new int[26];
            int n = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n; i++) {
                char[] chars = reader.readLine().toCharArray();
                int pos = (int) Math.pow(10, chars.length - 1);

                for (char character : chars) {
                    arr[character - 'A'] += pos;
                    pos /= 10;
                }
            }

            Arrays.sort(arr);

            int answer = 0;

            for (int i = arr.length - 1, value = 9; i > arr.length - 10; i--) {
                answer += arr[i] * value;
                value--;
            }

            writer.write(answer + "");
        }
    }

}