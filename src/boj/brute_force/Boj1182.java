package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1182 {

    public static int[] arr;

    public static int answer = 0;

    public static int n;

    public static int s;

    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            s = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n];

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            go(0, 0);
            answer = s == 0 ? answer - 1 : answer;
            writer.write(answer + "");
            writer.close();
        }
    }

    public static void go(int index, int sum) throws IOException {
        if (index == n) {
            if (sum == s) {
                answer++;
            }
            return;
        }

        go(index + 1, sum + arr[index]); // O
        go(index + 1, sum); // X
    }

}