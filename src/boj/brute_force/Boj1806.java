package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1806 {

    public static int[] arr;

    public static int n;

    public static int s;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            s = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n + 1];

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            writer.write(go() + "");
        }
    }

    public static int go() {
        int answer = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        int right = 0;

        while (right <= n) { // 마지막 항을 더하게되면 right + 1 == n이므로 비교하는 반복문이 n까지는 돌아야함
            if (sum < s) {
                sum += arr[right];
                right++;
            } else {
                answer = Math.min(answer, right - left);
                sum -= arr[left];
                left++;
            }
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

}