package boj.brute_force;

import java.io.*;

public class Boj3085 {

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(reader.readLine());
            int answer = 0;
            char[][] arr = new char[n][n];

            for (int i = 0; i < n; i++) {
                String line = reader.readLine();
                for (int j = 0; j < n; j++) {
                    arr[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < n - 1) {
                        swap(arr, i, j, i + 1, j);
                        int temp = check(arr, i, i + 1, j, j);
                        swap(arr, i, j, i + 1, j);
                        if (answer < temp) {
                            answer = temp;
                        }
                    }
                    if (j < n - 1) {
                        swap(arr, i, j + 1, i, j);
                        int temp = check(arr, i, i, j, j + 1);
                        swap(arr, i, j + 1, i, j);
                        if (answer < temp) {
                            answer = temp;
                        }
                    }
                }
            }

            writer.write(answer + "");
        }
    }

    public static void swap(char[][] arr, int aLeft, int aRight, int bLeft, int bRight) {
        char temp = arr[aLeft][aRight];
        arr[aLeft][aRight] = arr[bLeft][bRight];
        arr[bLeft][bRight] = temp;
    }

    public static int check(char[][] arr, int startLow, int endLow, int startCols, int endCols) {
        int n = arr.length;
        int answer = 1;

        for (int i = startLow; i <= endLow; i++) {
            int cnt = 1;
            for (int j = 1; j < n; j++) {
                if (arr[i][j] == arr[i][j - 1]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                if (answer < cnt) {
                    answer = cnt;
                }
            }
        }

        for (int i = startCols; i <= endCols; i++) {
            int cnt = 1;
            for (int j = 1; j < n; j++) {
                if (arr[j][i] == arr[j - 1][i]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                if (answer < cnt) {
                    answer = cnt;
                }
            }
        }

        return answer;
    }

}


