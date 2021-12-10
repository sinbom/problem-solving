package boj.bfs;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1963 {

    public static boolean[] prime = new boolean[10000];

    public static boolean[] check = new boolean[10000];

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());

            prime[0] = prime[1] = true;
            for (int i = 2; i * i < 10000; i++) {
                for (int j = i * i; j < 10000; j += i) {
                    prime[j] = true;
                }
            }

            for (int i = 0; i < t; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int s = Integer.parseInt(stringTokenizer.nextToken());
                int e = Integer.parseInt(stringTokenizer.nextToken());
                int answer = bfs(s, e);
                writer.write(answer == -1 ? "Impossible\n" : answer + "\n");
                Arrays.fill(check, false);
            }
        }
    }

    public static int bfs(int s, int e) {
        Queue<int[]> queue = new LinkedList<>();
        check[s] = true;
        queue.add(new int[]{s, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cur = poll[0];
            int cnt = poll[1];

            if (cur == e) {
                return cnt;
            }

            char[] chars = String.valueOf(cur).toCharArray();

            for (int i = 0; i < 4; i++) { // 1000의 자리부터 1의자리까지
                char origin = chars[i];
                for (int j = 0; j <= 9; j++) { // 0 부터 9까지 대입 가능
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    chars[i] = (char) (j + '0');
                    int next = Integer.parseInt(new String(chars));

                    if (check[next] || prime[next]) {
                        continue;
                    }

                    check[next] = true;
                    queue.add(new int[]{next, cnt + 1});
                }
                chars[i] = origin;
            }
        }

        return -1;
    }

}