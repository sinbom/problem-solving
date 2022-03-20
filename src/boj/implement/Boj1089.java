package boj.implement;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class Boj1089 {

    public static final String[] NUMS = {
            "####.##.##.####",
            "..#..#..#..#..#",
            "###..#####..###",
            "###..####..####",
            "#.##.####..#..#",
            "####..###..####",
            "####..####.####",
            "###..#..#..#..#",
            "####.#####.####",
            "####.####..####"
    };

    public static String[] map = {"", "", "", "", ""};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int strLen = 4 * n - 1;
            int totalSize = 1;
            int[] digitCount = new int[n];
            HashSet<Double>[] set = new HashSet[n];

            for (int i = 0; i < 5; i++) {
                map[i] = reader.readLine();
            }

            for (int i = 0; i < n; i++) {
                set[i] = new HashSet<>();
            }

            Arrays.fill(digitCount, 1);

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < strLen; j += 4) {
                    int index = j / 4;

                    for (int k = 0; k < 10; k++) {
                        double value = Math.pow(10, n - 1 - index) * k;

                        if (check(i, j, k)) {
                            if (i == 0) {
                                set[index].add(value);
                            }
                        } else {
                            set[index].remove(value);
                        }
                    }

                    if (set[index].size() == 0) {
                        writer.write("-1");
                        return;
                    }

                    if (i == 4) {
                        totalSize = totalSize * set[index].size();
                        for (int k = 0; k < n; k++) {
                            if (k != index) digitCount[k] = digitCount[k] * set[index].size();
                        }
                    }
                }
            }

            double sum = 0;

            for (int i = 0; i < n; i++) {
                double multi = (double) digitCount[i] / totalSize;
                sum += set[i]
                        .stream()
                        .mapToDouble(x -> x)
                        .map(x -> x * multi)
                        .sum();
            }

            writer.write(sum + "");
        }
    }

    public static boolean check(int row, int index, int compareTo) {
        for (int i = 0; i < 3; i++) {
            if (map[row].charAt(index + i) != '.' && map[row].charAt(index + i) != NUMS[compareTo].charAt(row * 3 + i)) {
                return false;
            }
        }

        return true;
    }
}


