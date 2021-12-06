package boj.brute_force;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1644 {

    public static List<Integer> list;

    public static boolean[] check;

    public static int n;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            check = new boolean[n + 1];
            check[0] = check[1] = true;
            list = new ArrayList<>(n);

            for (int i = 2; i * i <= n; i++) {
                if (check[i]) {
                    continue;
                }

                for (int j = i * i; j <= n; j += i) {
                    check[j] = true;
                }
            }

            for (int i = 2; i <= n; i++) {
                if (!check[i]) {
                    list.add(i);
                }
            }
            list.add(0);

            writer.write(go() + "");
        }
    }

    public static int go() {
        int answer = 0;
        int sum = 0;
        int left = 0;
        int right = 0;

        while (right <= list.size() - 1) {
            if (sum < n) {
                sum += list.get(right);
                right++;
            } else {
                sum -= list.get(left);
                left++;
            }

            if (sum == n) {
                answer++;
            }
        }

        return answer;
    }

}