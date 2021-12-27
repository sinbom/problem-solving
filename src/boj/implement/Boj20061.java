package boj.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj20061 {

    public static int answer;

    public static int[][] blue;

    public static int[][] green;

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            answer = 0;
            blue = new int[4][6];
            green = new int[6][4];
            int n = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int t = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int y = Integer.parseInt(stringTokenizer.nextToken());

                moveBlock(t, x, y);
                getScore();
                pushGreen(checkGreen());
                pushBlue(checkBlue());
            }

            writer.write(answer + "\n" + count());
        }
    }

    public static void moveBlock(int t, int x, int y) {
        int index;

        switch (t) {
            case 1:
                blue[x][0] = 1;
                green[0][y] = 1;
                index = 1;
                while (index < 6 && blue[x][index] == 0) {
                    blue[x][index - 1] = 0;
                    blue[x][index] = 1;
                    index++;
                }
                index = 1;
                while (index < 6 && green[index][y] == 0) {
                    green[index - 1][y] = 0;
                    green[index][y] = 1;
                    index++;
                }
                break;
            case 2:
                blue[x][0] = 1;
                blue[x][1] = 1;
                green[0][y] = 1;
                green[0][y + 1] = 1;
                index = 2;
                while (index < 6 && blue[x][index] == 0) {
                    blue[x][index - 2] = 0;
                    blue[x][index] = 1;
                    index++;
                }
                index = 1;
                while (index < 6 && green[index][y] == 0 && green[index][y + 1] == 0) {
                    green[index - 1][y] = 0;
                    green[index - 1][y + 1] = 0;
                    green[index][y] = 1;
                    green[index][y + 1] = 1;
                    index++;
                }
                break;
            case 3:
                blue[x][0] = 1;
                blue[x + 1][0] = 1;
                green[0][y] = 1;
                green[1][y] = 1;
                index = 1;
                while (index < 6 && blue[x][index] == 0 && blue[x + 1][index] == 0) {
                    blue[x][index - 1] = 0;
                    blue[x + 1][index - 1] = 0;
                    blue[x][index] = 1;
                    blue[x + 1][index] = 1;
                    index++;
                }
                index = 2;
                while (index < 6 && green[index][y] == 0) {
                    green[index - 2][y] = 0;
                    green[index][y] = 1;
                    index++;
                }
                break;
        }
    }

    public static void getScore() {
        for (int i = 5; i >= 2; i--) {
            int count = 0;

            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 0) {
                    break;
                } else {
                    count++;
                }
            }
            if (count == 4) {
                answer++;
                cleanGreen(i);
                i++;
            }
        }

        for (int i = 5; i >= 2; i--) {
            int count = 0;

            for (int j = 0; j < 4; j++) {
                if (blue[j][i] == 0) {
                    break;
                } else {
                    count++;
                }
            }
            if (count == 4) {
                answer++;
                cleanBlue(i);
                i++;
            }
        }
    }

    public static void cleanGreen(int line) {
        for (int i = line; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = green[i - 1][j];
            }
        }
    }

    public static void cleanBlue(int line) {
        for (int j = line; j > 0; j--) {
            for (int i = 0; i < 4; i++) {
                blue[i][j] = blue[i][j - 1];
            }
        }
    }

    public static int checkGreen() {
        int count = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 1) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public static int checkBlue() {
        int count = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++)
                if (blue[j][i] == 1) {
                    count++;
                    break;
                }
        }

        return count;
    }

    public static void pushGreen(int count) {
        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = green[i - count][j];
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = 0;
            }
        }
    }

    public static void pushBlue(int count) {
        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = blue[j][i - count];
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                blue[j][i] = 0;
            }
        }
    }

    public static int count() {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (blue[i][j] == 1) {
                    count++;
                }
                if (green[j][i] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}