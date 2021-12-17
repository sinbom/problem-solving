package boj.brute_force;

import java.io.*;

public class Boj1107 {

    public static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            String line = reader.readLine();
            int n = Integer.parseInt(line);
            int m = Integer.parseInt(reader.readLine());

            if (m != 0) {
                for (String s : reader.readLine().split(" ")) {
                    broken[Integer.parseInt(s)] = true;
                }
            }

            int answer = getDiff(n, 100);

            if (n == 100) {
                writer.write(0 + "");
            } else if (line.length() >= answer) {
                writer.write(answer + "");
            } else {
                for (int i = 0; i < 999894; i++) {
                    int length = possible(i);
                    if (length > 0) {
                        int count = getDiff(i, n);
                        if (answer > length + count) {
                            answer = length + count;
                        }
                    }
                }
                writer.write(answer + "");
            }

        }
    }

    public static int possible(int c) {
        int length = 0;
        if (c == 0) {
            if (broken[0]) {
                return 0;
            } else {
                return 1;
            }
        }
        while (c > 0) {
            if (broken[c % 10]) {
                return 0;
            }
            length++;
            c /= 10;
        }
        return length;
    }

    public static int getDiff(int n, int m) {
        int diff = n - m;
        return diff < 0 ? -diff : diff;
    }

}


