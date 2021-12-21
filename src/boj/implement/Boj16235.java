package boj.implement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj16235 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            List<List<Integer>> trees = new ArrayList<>(n * n);
            int[][] map = new int[n][n];
            int[][] a = new int[n][n];
            int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
            int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    a[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    map[i][j] = 5;
                    trees.add(new ArrayList<>());
                }
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int age = Integer.parseInt(stringTokenizer.nextToken());
                int index = x * n + y;
                int size = trees.get(index).size();
                int idx = 0;

                for (int j = 0; j < size; j++) {
                    if (trees.get(index).get(j) >= age) {
                        break;
                    }
                    idx++;
                }

                trees
                        .get(index)
                        .add(idx, age);
            }

            while (k-- > 0) {
                for (int i = 0; i < trees.size(); i++) {
                    List<Integer> tree = trees.get(i);
                    int x = i / n;
                    int y = i % n;
                    int j = 0;

                    // 봄
                    while (j < tree.size()) {
                        Integer age = tree.get(j);

                        if (map[x][y] < age) {
                            break;
                        }

                        map[x][y] -= age;
                        tree.set(j, age + 1);
                        j++;
                    }

                    // 여름
                    for (int l = tree.size() - 1; l >= j; l--) {
                        map[x][y] += tree.remove(l) / 2;
                    }
                }

                // 가을
                for (int i = 0; i < trees.size(); i++) {
                    List<Integer> tree = trees.get(i);
                    int x = i / n;
                    int y = i % n;

                    for (int j = 0; j < tree.size(); j++) {
                        if (tree.get(j) % 5 != 0) {
                            continue;
                        }

                        for (int l = 0; l < dx.length; l++) {
                            int nx = x + dx[l];
                            int ny = y + dy[l];

                            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                                continue;
                            }

                            trees
                                    .get(nx * n + ny)
                                    .add(0, 1);
                        }
                    }
                }

                // 겨울
                for (int i = 0; i < a.length; i++) {
                    for (int j = 0; j < a[i].length; j++) {
                        map[i][j] += a[i][j];
                    }
                }
            }

            int answer = trees
                    .stream()
                    .mapToInt(List::size)
                    .sum();

            writer.write(answer + "");
        }
    }

}