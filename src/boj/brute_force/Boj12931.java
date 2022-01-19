package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj12931 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] arr = new int[n];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int sum = 0;
            int answer = 0;

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
                sum += arr[i];
            }

            while (sum != 0) {
                int num = 0;

                for (int i = 0; i < n; i++) {
                    if (arr[i] % 2 == 1) {
                        arr[i]--;
                        num++;
                    }
                }

                if (num > 0) {
                    sum -= num;
                    answer += num;
                } else {
                    for (int i = 0; i < n; i++) {
                        arr[i] /= 2;
                    }
                    sum /= 2;
                    answer++;
                }
            }

            writer.write(answer + "");
        }
    }

}








