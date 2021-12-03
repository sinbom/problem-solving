package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj14500 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int answer = 0;
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            int[][] arr = new int[n][m];

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // ㅡ자 검사(회전 가능)
                    if (j < m - 3) {
                        int sum = 0;
                        sum += arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i][j + 3];
                        if (answer < sum) {
                            answer = sum;
                        }
                    }
                    // ㅡ자 검사(회전 가능)
                    if (i < n - 3) {
                        int sum = 0;
                        sum += arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 3][j];
                        if (answer < sum) {
                            answer = sum;
                        }
                    }
                    // ㅁ자 검사
                    if (i < n - 1 && j < m - 1) {
                        int sum = 0;
                        sum += arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1];
                        if (answer < sum) {
                            answer = sum;
                        }
                    }

                    // ㄴ, ㄹ, ㅗ(회전 및 대칭 가능)
                    if (i < n - 2 && j < m - 1) {
                        // ㄴ자 검사
                        int sum = 0;
                        sum += arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 2][j + 1];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 2][j] + arr[i + 2][j + 1];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 2][j + 1];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 2][j];
                        if (answer < sum) {
                            answer = sum;
                        }

                        // ㄹ자 검사
                        sum = 0;
                        sum += arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j + 1];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j] + arr[i + 2][j];
                        if (answer < sum) {
                            answer = sum;
                        }

                        // ㅗ자 검사
                        sum = 0;
                        sum += arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j + 1];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j];
                        if (answer < sum) {
                            answer = sum;
                        }
                    }

                    if (i < n - 1 && j < m - 2) {
                        // ㄴ자 검사
                        int sum = 0;
                        sum += arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 2];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2] + arr[i][j + 2];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i][j] + arr[i + 1][j] + arr[i][j + 1] + arr[i][j + 2];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2];
                        if (answer < sum) {
                            answer = sum;
                        }

                        // ㄹ자 검사
                        sum = 0;
                        sum += arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j + 2];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i + 1][j] + arr[i + 1][j + 1] + arr[i][j + 1] + arr[i][j + 2];
                        if (answer < sum) {
                            answer = sum;
                        }

                        // ㅗ자 검사
                        sum = 0;
                        sum += arr[i + 1][j] + arr[i + 1][j + 1] + arr[i][j + 1] + arr[i + 1][j + 2];
                        if (answer < sum) {
                            answer = sum;
                        }
                        sum = 0;
                        sum += arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1];
                        if (answer < sum) {
                            answer = sum;
                        }
                    }
                }
            }

            writer.write(answer + "");
        }
    }

}