package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2618 {

    public static int[][] dp = new int[1002][1002];

    public static int[][] list = new int[1002][2];

    public static int incident;

    public static int road;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            road = Integer.parseInt(reader.readLine());
            incident = Integer.parseInt(reader.readLine());

            for (int x = 1; x <= incident; x++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

                list[x][0] = Integer.parseInt(stringTokenizer.nextToken());
                list[x][1] = Integer.parseInt(stringTokenizer.nextToken());
            }

            writer.write(police(1, 0, 0) + "\n");

            int one = 0;
            int two = 0;

            for (int index = 1; index <= incident; index++) {
                int oneRemain = distance(1, one, index);

                if (dp[one][two] - oneRemain == dp[index][two]) {
                    one = index;
                    writer.write("1\n");
                } else {
                    two = index;
                    writer.write("2\n");
                }
            }
        }
    }

    public static int police(int index, int one, int two) {
        if (index > incident) {
            return 0;
        }

        if (dp[one][two] != 0) {
            return dp[one][two];
        }

        int oneMove = police(index + 1, index, two) + distance(1, one, index);
        int twoMove = police(index + 1, one, index) + distance(2, two, index);

        return dp[one][two] = Math.min(oneMove, twoMove);
    }

    public static int distance(int sep, int start, int end) {
        int xStart = list[start][0];
        int xEnd = list[end][0];
        int yStart = list[start][1];
        int yEnd = list[end][1];

        if (start == 0) {
            if (sep == 1) {
                xStart = yStart = 1;
            } else {
                xStart = yStart = road;
            }
        }

        return Math.abs(xStart - xEnd) + Math.abs(yStart - yEnd);
    }
}