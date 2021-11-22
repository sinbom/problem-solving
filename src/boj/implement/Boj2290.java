package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2290 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = reader.readLine().split(" ");
            int s = Integer.parseInt(line[0]);
            String n = line[1];
            int height = 2 * s + 3;
            int width = s + 2;
            int length = n.length();
            char[][] arr = new char[height][length * width + length - 1];

            for (char[] chars : arr) {
                Arrays.fill(chars, ' ');
            }

            for (int i = 0, blank = -1; i < length; i++, blank++) {
                //우상 세로선
                if (n.charAt(i) != '5' && n.charAt(i) != '6') {
                    for (int j = 1; j < height / 2; j++) {
                        arr[j][width * (i + 1) + blank] = '|';
                    }
                }
                //우하 세로선
                if (n.charAt(i) != '2') {
                    for (int j = height / 2 + 1; j < height - 1; j++) {
                        arr[j][width * (i + 1) + blank] = '|';
                    }
                }
                //좌상 세로선
                if (n.charAt(i) == '4' || n.charAt(i) == '5' || n.charAt(i) == '6' || n.charAt(i) == '8' || n.charAt(i) == '9' || n.charAt(i) == '0') {
                    for (int j = 1; j < height / 2; j++) {
                        arr[j][width * (i + 1) + blank - (width - 1)] = '|';
                    }
                }
                //좌하 세로선
                if (n.charAt(i) == '2' || n.charAt(i) == '6' || n.charAt(i) == '8' || n.charAt(i) == '0') {
                    for (int j = height / 2 + 1; j < height - 1; j++) {
                        arr[j][width * (i + 1) + blank - (width - 1)] = '|';
                    }
                }
                //가로선
                if (n.charAt(i) != '1') {
                    for (int j = 0; j < height; j++) {
                        if (j == 0 && n.charAt(i) != '4') {
                            for (int k = 1; k <= s; k++) {
                                arr[j][width * (i + 1) + blank - k] = '-';
                            }
                        }
                        if (j == height / 2 && n.charAt(i) != '7' && n.charAt(i) != '0') {
                            for (int k = 1; k <= s; k++) {
                                arr[j][width * (i + 1) + blank - k] = '-';
                            }
                        }
                        if (j == height - 1 && n.charAt(i) != '4' && n.charAt(i) != '7') {
                            for (int k = 1; k <= s; k++) {
                                arr[j][width * (i + 1) + blank - k] = '-';
                            }
                        }
                    }
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (char[] chars : arr) {
                for (char aChar : chars) {
                    stringBuilder.append(aChar);
                }
                stringBuilder.append('\n');
            }

            System.out.println(stringBuilder);
        }
    }
}