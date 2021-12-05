package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1062 {

    public static int[] arr;

    public static int n;

    public static int k;

    public static int answer = 0;

    public static int check = 0;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(stringTokenizer.nextToken());
            k = Integer.parseInt(stringTokenizer.nextToken());
            arr = new int[n];

            if (k < 5) {
                writer.write("0");
                return;
            }
            if (k == 26) {
                writer.write(n + "");
                return;
            }

            check |= 1 << ('a' - 'a');
            check |= 1 << ('c' - 'a');
            check |= 1 << ('i' - 'a');
            check |= 1 << ('n' - 'a');
            check |= 1 << ('t' - 'a');
            k -= 5;

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();

                for (int j = 0; j < s.length(); j++) {
                    arr[i] |= 1 << s.charAt(j) - 'a';
                }
            }

            go(0, 0);

            writer.write(answer + "");
        }
    }

    public static void go(int index, int count) {
        if (count == k) {
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                if ((arr[i] & check) == arr[i]) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);

            return;
        }

        for (int i = index; i < 26; i++) {
            if ((check & (1 << i)) == 0) {
                check |= 1 << i;
                go(i + 1, count + 1);
                check &= ~(1 << i);
            }
        }
    }

}