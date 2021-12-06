package boj.brute_force;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1208 {

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int half = n / 2;
            List<Long> leftSum = new ArrayList<>((int) Math.pow(2, half));
            List<Long> rightSum = new ArrayList<>((int) Math.pow(2, n - half));
            arr = new int[n];

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            go(0, half, 0, leftSum);
            go(half, n, 0, rightSum);
            Collections.sort(leftSum);
            Collections.sort(rightSum);

            long answer = 0;
            int left = 0;
            int right = rightSum.size() - 1;
            int cnt = 0;

            while (left < leftSum.size() && right >= 0) {
                if (leftSum.get(left) + rightSum.get(right) == s) {
                    if (cnt == 0) {
                        cnt++;
                        int idx = right - 1;
                        while (idx >= 0 && rightSum.get(idx).equals(rightSum.get(right))) {
                            cnt++;
                            idx--;
                        }
                    }
                    answer += cnt;
                    left++;
                } else if (leftSum.get(left) + rightSum.get(right) < s) {
                    left++;
                    cnt = 0;
                } else {
                    right--;
                    cnt = 0;
                }
            }

            answer = s == 0 ? answer - 1 : answer;
            writer.write(answer + "");
        }
    }

    public static void go(int s, int e, long sum, List<Long> list) {
        if (s == e) {
            list.add(sum);
            return;
        }
        go(s + 1, e, sum + arr[s], list);
        go(s + 1, e, sum, list);
    }


}