package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj1525 {

    public static String correct = "123456780";

    public static Map<String, Integer> map = new HashMap<>();

    public static int[] dx = {-1, 1, 0, 0};

    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 3; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                for (int j = 0; j < 3; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    stringBuilder.append(num);
                }
            }

            map.put(stringBuilder.toString(), 0);

            writer.write(bfs(stringBuilder.toString()));
        }
    }

    public static int bfs(String init) {
        Queue<String> q = new LinkedList<>();
        q.add(init);

        while (!q.isEmpty()) {
            String current = q.poll();
            int move = map.get(current);
            int empty = current.indexOf('0');
            int px = empty % 3;
            int py = empty / 3;

            if (current.equals(correct)) {
                return move;
            }

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx < 0 || ny < 0 || nx > 2 || ny > 2) continue;

                int nPos = ny * 3 + nx;
                char ch = current.charAt(nPos);
                
                String next = current.replace(ch, 'c');
                next = next.replace('0', ch);
                next = next.replace('c', '0');

                if (!map.containsKey(next)) {
                    q.add(next);
                    map.put(next, move + 1);
                }
            }
        }

        return -1;
    }
}

