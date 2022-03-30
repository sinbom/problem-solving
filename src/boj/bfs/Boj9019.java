package boj.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9019 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());

            while (t-- > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken());
                boolean[] visit = new boolean[10000];
                visit[a] = true;

                Queue<Register> que = new LinkedList<>();
                que.add(new Register(a, ""));

                while (!que.isEmpty()) {
                    Register cur = que.poll();

                    if (cur.num == b) {
                        writer.write(cur.command + "\n");
                        break;
                    }

                    if (!visit[cur.d()]) {
                        que.add(new Register(cur.d(), cur.command + "D"));
                        visit[cur.d()] = true;
                    }
                    if (!visit[cur.s()]) {
                        que.add(new Register(cur.s(), cur.command + "S"));
                        visit[cur.s()] = true;
                    }
                    if (!visit[cur.l()]) {
                        que.add(new Register(cur.l(), cur.command + "L"));
                        visit[cur.l()] = true;
                    }
                    if (!visit[cur.r()]) {
                        que.add(new Register(cur.r(), cur.command + "R"));
                        visit[cur.r()] = true;
                    }
                }
            }
        }
    }

    public static class Register {

        private final int num;

        private final String command;

        Register(int num, String command) {
            this.num = num;
            this.command = command;
        }

        public int d() {
            return (num * 2) % 10000;
        }

        public int s() {
            return num == 0 ? 9999 : num - 1;
        }

        public int l() {
            return num % 1000 * 10 + num / 1000;
        }

        public int r() {
            return num % 10 * 1000 + num / 10;
        }
    }
}


