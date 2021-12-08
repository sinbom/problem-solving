package boj.dfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12886 {

    public static boolean[][] check = new boolean[1500][1500];

    public static int[] temp = new int[3];

    public static int sum = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < temp.length; i++) {
                temp[i] = Integer.parseInt(stringTokenizer.nextToken());
                sum += temp[i];
            }

            if (sum % 3 != 0) {
                writer.write("0");
                return;
            }

//            writer.write(bfs(arr) + "");
            writer.write(dfs(temp[0], temp[1], temp[2]) ? "1" : "0");
        }
    }

    public static int bfs(int[] arr) {
        Queue<int[]> queue = new LinkedList<>();
        check[arr[0]][arr[1]] = true;
        check[arr[1]][arr[0]] = true;
        check[arr[0]][arr[2]] = true;
        check[arr[2]][arr[0]] = true;
        check[arr[1]][arr[2]] = true;
        check[arr[2]][arr[1]] = true;
        queue.add(arr);

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[0] == poll[1] && poll[1] == poll[2]) {
                return 1;
            }

            for (int i = 0; i < poll.length; i++) {
                for (int j = 0; j < poll.length; j++) {
                    if (poll[i] < poll[j]) {
                        int t1 = poll[i] * 2;
                        int t2 = poll[j] - poll[i];
                        int t3 = sum - (t1 + t2);

                        if (!check[t1][t2]) {
                            check[t1][t2] = true;
                            check[t2][t1] = true;
                            check[t1][t3] = true;
                            check[t3][t1] = true;
                            check[t2][t3] = true;
                            check[t3][t2] = true;
                            queue.add(new int[]{t1, t2, t3});
                        }
                    }
                }
            }
        }

        return 0;
    }

    public static boolean dfs(int left, int mid, int right) {
        check[left][mid] = true;
        check[mid][left] = true;
        check[left][right] = true;
        check[right][left] = true;
        check[mid][right] = true;
        check[right][mid] = true;

        if (left == mid && mid == right) {
            return true;
        }

        int[] temp = {left, mid, right};

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (temp[i] < temp[j]) {
                    int t1 = temp[i] * 2;
                    int t2 = temp[j] - temp[i];
                    int t3 = sum - (t1 + t2);

                    if (!check[t1][t2]) {
                        check[t1][t2] = true;
                        check[t2][t1] = true;
                        check[t1][t3] = true;
                        check[t3][t1] = true;
                        check[t2][t3] = true;
                        check[t3][t2] = true;
                        if (dfs(t1, t2, t3)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

}