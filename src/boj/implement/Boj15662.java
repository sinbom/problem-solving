package boj.implement;

import java.io.*;

public class Boj15662 {

    public static int[] answer;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());
            int[][] circles = new int[t + 1][8];
            answer = new int[t + 1];

            for (int i = 1; i < t + 1; i++) {
                String s = reader.readLine();
                for (int j = 0; j < 8; j++) {
                    circles[i][j] = Character.getNumericValue(s.charAt(j));
                }
            }

            int k = Integer.parseInt(reader.readLine());

            while (k-- > 0) {
                String[] s = reader.readLine().split(" ");
                int number = Integer.parseInt(s[0]);
                int direction = Integer.parseInt(s[1]);

                // 좌측
                int d = direction;
                int o = circles[number][calcIndex(number, -2)];

                for (int i = number; i > 1; i--) {
                    if (o == circles[i - 1][calcIndex(i - 1, 2)]) {
                        break;
                    }
                    o = circles[i - 1][calcIndex(i - 1, -2)];
                    d = -d;
                    rotate(i - 1, d);
                }

                // 우측
                d = direction;
                o = circles[number][(answer[number] + 2 + 8) % 8];

                for (int i = number; i < t; i++) {
                    if (o == circles[i + 1][calcIndex(i + 1, -2)]) {
                        break;
                    }
                    o = circles[i + 1][calcIndex(i + 1, 2)];
                    d = -d;
                    rotate(i + 1, d);
                }

                // 자기 자신
                rotate(number, direction);
            }

            int count = 0;

            for (int i = 1; i < answer.length; i++) {
                if (circles[i][answer[i]] == 1) {
                    count++;
                }
            }

            writer.write(count + "");
        }
    }

    public static void rotate(int index, int direction) {
        answer[index] = calcIndex(index, -direction);
    }

    public static int calcIndex(int index, int move) {
        return (answer[index] + move + 8) % 8;
    }

}