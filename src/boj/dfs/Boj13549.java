package boj.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Boj13549 {

    public static int[] check;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            check = new int[100001];
            Arrays.fill(check, -1);

            bfs(n, k);
            writer.write(check[k] + "");
        }
    }

    public static void bfs(int n, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(n);
        check[n] = 0;

        while (!queue.isEmpty()) {
            int position = queue.poll();

            if (position == k) {
                return;
            }

            if (position * 2 <= 100000 && check[position * 2] == -1) {
                queue.addFirst(position * 2);
                check[position * 2] = check[position];
            }

            if (position > 0 && check[position - 1] == -1) {
                queue.offer(position - 1);
                check[position - 1] = check[position] + 1;
            }

            if (position < 100000 && check[position + 1] == -1) {
                queue.offer(position + 1);
                check[position + 1] = check[position] + 1;
            }
        }
    }

}