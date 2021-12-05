package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj2580 {

    public static int[] arr = new int[9 * 9];

    public static boolean[][] xcheck = new boolean[9][10];

    public static boolean[][] ycheck = new boolean[9][10];

    public static boolean[][][] xycheck = new boolean[9][9][10];

    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static boolean flag = false;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < 9; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < 9; j++) {
                    int number = Integer.parseInt(stringTokenizer.nextToken());
                    arr[i * 9 + j] = number;
                    xcheck[i][number] = ycheck[j][number] = xycheck[i / 3 * 3][j / 3 * 3][number] = true;
                }
            }

            go(0);
            writer.close();
        }
    }

    public static void go(int position) throws IOException {
        if (flag) {
            return;
        }

        if (position == 81) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    writer.write(arr[i * 9 + j] + " ");
                }
                writer.newLine();
            }
            flag = true;

            return;
        }

        if (arr[position] != 0) {
            go(position + 1);
        } else {
            int x = position / 9;
            int y = position % 9;
            int sx = x / 3 * 3;
            int sy = y / 3 * 3;

            for (int i = 1; i <= 9; i++) {
                if (check(position, i)) {
                    arr[position] = i;
                    xcheck[x][i] = ycheck[y][i] = xycheck[sx][sy][i] = true;
                    go(position + 1);
                    xcheck[x][i] = ycheck[y][i] = xycheck[sx][sy][i] = false;
                    arr[position] = 0;
                }
            }
        }
    }

    public static boolean check(int position, int number) {
        int x = position / 9;
        int y = position % 9;

        return !xcheck[x][number] && !ycheck[y][number] && !xycheck[x / 3 * 3][y / 3 * 3][number];
    }

}