package boj.implement;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Boj17140 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int r = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int c = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int[][] map = new int[100][100];
            int rSize = 3;
            int cSize = 3;

            for (int i = 0; i < 3; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < 3; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            if (map[r][c] == k) {
                writer.write("0");
                return;
            }

            Map<Integer, Number> hashMap = new HashMap<>();
            int answer = -1;

            for (int t = 1; t <= 100; t++) {
                if (rSize >= cSize) { // 행 정렬
                    int max = cSize;

                    for (int i = 0; i < rSize; i++) {
                        for (int j = 0; j < cSize; j++) {
                            int value = map[i][j];

                            if (map[i][j] == 0) {
                                continue;
                            }

                            map[i][j] = 0;

                            hashMap
                                    .computeIfAbsent(
                                            value,
                                            key -> new Number(value, 0)
                                    )
                                    .increase();
                        }

                        List<Number> numbers = hashMap
                                .values()
                                .stream()
                                .sorted()
                                .collect(Collectors.toList());

                        int length = 0;

                        for (int j = 0; j < numbers.size(); j++) {
                            map[i][length] = numbers.get(j).value;
                            map[i][length + 1] = numbers.get(j).count;
                            length += 2;
                        }

                        hashMap.clear();
                        max = Math.max(max, length);
                    }

                    cSize = max;
                } else { // 열 정렬
                    int max = rSize;

                    for (int j = 0; j < cSize; j++) {
                        for (int i = 0; i < rSize; i++) {
                            int value = map[i][j];

                            if (map[i][j] == 0) {
                                continue;
                            }

                            map[i][j] = 0;

                            hashMap
                                    .computeIfAbsent(
                                            value,
                                            key -> new Number(value, 0)
                                    )
                                    .increase();
                        }

                        List<Number> numbers = hashMap
                                .values()
                                .stream()
                                .sorted()
                                .collect(Collectors.toList());

                        int length = 0;

                        for (int i = 0; i < numbers.size(); i++) {
                            map[length][j] = numbers.get(i).value;
                            map[length + 1][j] = numbers.get(i).count;
                            length += 2;
                        }

                        hashMap.clear();
                        max = Math.max(max, length);
                    }

                    rSize = max;
                }

                if (map[r][c] == k) {
                    answer = t;
                    break;
                }
            }

            writer.write(answer + "");
        }

    }

    public static class Number implements Comparable<Number> {

        private final int value;
        private int count;

        public Number(int value, int count) {
            this.value = value;
            this.count = count;
        }

        public void increase() {
            this.count++;
        }

        @Override
        public int compareTo(Number o) {
            int compare = this.count - o.count;

            if (compare == 0) {
                return this.value - o.value;
            }

            return compare;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Number number = (Number) o;
            return value == number.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

    }

}