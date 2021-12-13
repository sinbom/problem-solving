package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj12906 {

    public static Set<String> set = new HashSet<>();

    public static int[] dx = {0, 0, 1, 1, 2, 2};

    public static int[] dy = {1, 2, 0, 2, 0, 1};

    public static char[] chars = {'A', 'B', 'C'};

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] arr = new String[3];

            for (int i = 0; i < 3; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                if (stringTokenizer.nextToken().equals("0")) {
                    arr[i] = "";
                    continue;
                }
                arr[i] = stringTokenizer.nextToken();
            }

            writer.write(bfs(new Group(arr[0], arr[1], arr[2])) + "");
        }
    }

    public static boolean check(String a, String b, String c) {
        String[] s = {a, b, c};

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length(); j++) {
                if (s[i].charAt(j) != chars[i]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int bfs(Group group) {
        Queue<Group> queue = new LinkedList<>();
        queue.add(group);
        set.add(group.getKey());

        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Group current = queue.poll();
                String a = current.a;
                String b = current.b;
                String c = current.c;

                if (check(a, b, c)) {
                    return count;
                }

                for (int i = 0; i < dx.length; i++) {
                    String[] temp = {a, b, c};
                    if (temp[dx[i]].length() == 0) {
                        continue;
                    }

                    char move = temp[dx[i]].charAt(temp[dx[i]].length() - 1);
                    temp[dx[i]] = temp[dx[i]].substring(0, temp[dx[i]].length() - 1);
                    temp[dy[i]] += move;
                    Group next = new Group(temp[0], temp[1], temp[2]);
                    String key = next.getKey();

                    if (set.contains(key)) {
                        continue;
                    }

                    set.add(key);
                    queue.add(next);
                }
            }

            count++;
        }

        return -1;
    }

    public static class Group {

        private final String a;
        private final String b;
        private final String c;

        public Group(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public String getKey() {
            return a + " " + b + " " + c;
        }
    }


}