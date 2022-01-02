package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj17281 {

    public static int n;

    public static int[][] players;

    public static boolean[] select;

    public static int[] order;

    public static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            n = Integer.parseInt(reader.readLine());
            players = new int[n + 1][10];
            select = new boolean[10];
            order = new int[10];
            select[4] = true;
            order[4] = 1;

            for (int i = 1; i <= n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= 9; j++) {
                    players[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            go(2);
            writer.write(answer + "");
        }
    }

    public static void go(int num) {
        if (num == 10) {
            play();

            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (select[i]) {
                continue;
            }

            select[i] = true;
            order[i] = num;
            go(num + 1);
            select[i] = false;
        }
    }

    public static void play() {
        int score = 0;
        int startPlayer = 1;
        boolean[] base;

        for (int i = 1; i <= n; i++) {
            int outCnt = 0;
            base = new boolean[4];

            while (true) {
                boolean flag = false;

                for (int j = startPlayer; j <= 9; j++) {
                    int hitter = players[i][order[j]];

                    if (hitter == 0) {
                        outCnt++;
                    } else if (hitter == 1) {
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k == 3) {
                                    score++;
                                    base[k] = false;
                                    continue;
                                }
                                base[k] = false;
                                base[k + 1] = true;
                            }
                        }
                        base[1] = true;
                    } else if (hitter == 2) {
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k == 3 || k == 2) {
                                    score++;
                                    base[k] = false;
                                    continue;
                                }
                                base[k] = false;
                                base[k + 2] = true;
                            }
                        }
                        base[2] = true;
                    } else if (hitter == 3) {
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                score++;
                                base[k] = false;
                            }
                        }
                        base[3] = true;
                    } else if (hitter == 4) {
                        for (int k = 1; k <= 3; k++) {
                            if (base[k]) {
                                score++;
                                base[k] = false;
                            }
                        }
                        score++;
                    }

                    if (outCnt == 3) {
                        startPlayer = j + 1;
                        if (startPlayer == 10) {
                            startPlayer = 1;
                        }

                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    break;
                }

                startPlayer = 1;
            }
        }

        answer = Math.max(answer, score);
    }

}



