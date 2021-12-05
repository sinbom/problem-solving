package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2529 {

    public static char[] operator;

    public static boolean[] check = new boolean[10];

    public static int[] answer;

    public static long max = 0;

    public static long min = 9999999999L;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            answer = new int[n + 1];
            operator = new char[n];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                operator[i] = stringTokenizer.nextToken().charAt(0);
            }

            go(n, 0);

            String maxStr = max + "";
            String minStr = min + "";
            maxStr = maxStr.length() == n + 1 ? maxStr : "0" + maxStr;
            minStr = minStr.length() == n + 1 ? minStr : "0" + minStr;
            writer.write(maxStr + "\n" + minStr);
        }
    }

    public static void go(int n, int count) {
        if (count == n + 1) {
            long sum = answer[0];

            for (int i = 1; i < answer.length; i++) {
                sum = sum * 10 + answer[i];
            }
            min = Math.min(min, sum);
            max = Math.max(max, sum);

            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!check[i]) {
                if (count > 0) {
                    char op = operator[count - 1];
                    if (op == '>' && answer[count - 1] < i) {
                        break;
                    }
                    if (op == '<' && answer[count - 1] > i) {
                        continue;
                    }
                }
                check[i] = true;
                answer[count] = i;
                go(n, count + 1);
                check[i] = false;
            }
        }
    }

}