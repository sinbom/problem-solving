package boj.brute_force;

import java.io.*;
import java.util.*;

public class Boj17471 {

    public static int n;

    public static Position[] positions;

    public static List<List<Integer>> list = new ArrayList<>();

    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            positions = new Position[n + 1];
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            for (int i = 1; i <= n; i++) {
                int peopleNum = Integer.parseInt(stringTokenizer.nextToken());
                positions[i] = new Position(i, peopleNum);
            }

            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 1; i <= n; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int n = Integer.parseInt(stringTokenizer.nextToken());
                for (int j = 0; j < n; j++) {
                    int temp = Integer.parseInt(stringTokenizer.nextToken());
                    list.get(i).add(temp);
                }
            }

            for (int i = 1; i <= n / 2; i++) {
                comb(1, n, i, new ArrayList<>());
            }

            if (answer == Integer.MAX_VALUE) {
                answer = -1;
            }

            writer.write(answer + "\n");
        }
    }

    public static void comb(int start, int n, int r, ArrayList<Integer> A) {
        if (r == 0) {
            gerrymandering(A);
            return;
        }

        for (int i = start; i <= n; i++) {
            A.add(i);
            comb(i + 1, n, r - 1, A);
            A.remove(A.size() - 1);
        }
    }

    public static void gerrymandering(List<Integer> a) {
        if (isConnect(positions[a.get(0)].x, a, a.size())) {
            return;
        }

        List<Integer> b = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (a.contains(i)) {
                continue;
            }

            b.add(i);
        }

        if (isConnect(positions[b.get(0)].x, b, b.size())) {
            return;
        }

        int resultA = 0;

        for (int i = 0; i < a.size(); i++) {
            resultA += positions[a.get(i)].peopleNum;
        }

        int resultB = 0;

        for (int i = 0; i < b.size(); i++) {
            resultB += positions[b.get(i)].peopleNum;
        }

        int result = Math.abs(resultA - resultB);
        answer = Math.min(answer, result);
    }

    public static boolean isConnect(int num, List<Integer> arr, int size) {
        boolean[] visited = new boolean[n + 1];
        visited[num] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(num);

        int count = 1;
        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : list.get(current)) {
                if (!visited[next] && arr.contains(next)) {
                    visited[next] = true;
                    count++;
                    q.offer(next);
                }
            }
        }

        return count != size;
    }

    public static class Position {

        private final int x;
        private final int peopleNum;

        Position(int x, int peopleNum) {
            this.x = x;
            this.peopleNum = peopleNum;
        }
    }

}







