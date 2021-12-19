package boj.brute_force;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15661 {

    public static int[][] s;

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(reader.readLine());
            s = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    s[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            int answer = go(n, 0, new ArrayList<>(), new ArrayList<>());
            writer.write(answer + "");
        }
    }

    public static int go(int n, int index, List<Integer> first, List<Integer> second) {
        if (index == n) {
            if (first.size() < 1 || second.size() < 1) {
                return -1;
            }
            int t1 = 0;
            int t2 = 0;

            for (int i = 0; i < first.size(); i++) {
                for (int j = 0; j < first.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    t1 += s[first.get(i)][first.get(j)];
                }
            }

            for (int i = 0; i < second.size(); i++) {
                for (int j = 0; j < second.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    t2 += s[second.get(i)][second.get(j)];
                }
            }

            return Math.abs(t1 - t2);
        }
        int ans = Integer.MAX_VALUE;
        first.add(index);
        int t1 = go(n, index + 1, first, second);
        if (t1 != -1 && ans > t1) {
            ans = t1;
        }
        first.remove(first.size() - 1);
        second.add(index);
        int t2 = go(n, index + 1, first, second);
        if (t2 != -1 && ans > t2) {
            ans = t2;
        }
        second.remove(second.size() - 1);
        return ans;
    }

}