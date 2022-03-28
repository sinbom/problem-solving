package boj.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj5373 {

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int t = Integer.parseInt(reader.readLine());
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(reader.readLine());
                String[] input = reader.readLine().split(" ");
                char[][][] cube = new char[6][3][3];
                initCube(cube);

                for (int j = 0; j < n; j++) {
                    String face = input[j].substring(0, 1);
                    String d = input[j].substring(1);
                    chgCube(cube, face, d);

                }

                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        stringBuilder.append(cube[0][r][c]);
                    }
                    stringBuilder.append("\n");
                }

            }
            writer.write(stringBuilder.toString());
        }
    }

    public static void initCube(char[][][] cube) {
        for (int i = 0; i < 6; i++) {
            char color;
            switch (i) {
                case 0:
                    color = 'w';
                    break;
                case 1:
                    color = 'y';
                    break;
                case 2:
                    color = 'r';
                    break;
                case 3:
                    color = 'o';
                    break;
                case 4:
                    color = 'g';
                    break;
                default:
                    color = 'b';
                    break;
            }
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = color;
                }
            }
        }

    }

    public static void chgCube(char[][][] cube, String face, String d) {
        switch (face) {
            case "U":
                if (d.equals("+")) {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[j][2 - i] = cube[0][i][j];
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[0][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];
                    System.arraycopy(cube[2][0], 0, tmp2, 0, 3);
                    System.arraycopy(cube[5][0], 0, cube[2][0], 0, 3);
                    System.arraycopy(cube[3][0], 0, cube[5][0], 0, 3);
                    System.arraycopy(cube[4][0], 0, cube[3][0], 0, 3);
                    System.arraycopy(tmp2, 0, cube[4][0], 0, 3);
                } else {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[2 - j][i] = cube[0][i][j];
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[0][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];
                    System.arraycopy(cube[2][0], 0, tmp2, 0, 3);
                    System.arraycopy(cube[4][0], 0, cube[2][0], 0, 3);
                    System.arraycopy(cube[3][0], 0, cube[4][0], 0, 3);
                    System.arraycopy(cube[5][0], 0, cube[3][0], 0, 3);
                    System.arraycopy(tmp2, 0, cube[5][0], 0, 3);
                }
                break;
            case "D":
                if (d.equals("+")) {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[j][2 - i] = cube[1][i][j];
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[1][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];
                    System.arraycopy(cube[2][2], 0, tmp2, 0, 3);
                    System.arraycopy(cube[4][2], 0, cube[2][2], 0, 3);
                    System.arraycopy(cube[3][2], 0, cube[4][2], 0, 3);
                    System.arraycopy(cube[5][2], 0, cube[3][2], 0, 3);
                    System.arraycopy(tmp2, 0, cube[5][2], 0, 3);
                } else {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[2 - j][i] = cube[1][i][j];
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[1][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];
                    System.arraycopy(cube[2][2], 0, tmp2, 0, 3);
                    System.arraycopy(cube[5][2], 0, cube[2][2], 0, 3);
                    System.arraycopy(cube[3][2], 0, cube[5][2], 0, 3);
                    System.arraycopy(cube[4][2], 0, cube[3][2], 0, 3);
                    System.arraycopy(tmp2, 0, cube[4][2], 0, 3);
                }
                break;
            case "F":
                if (d.equals("+")) {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[j][2 - i] = cube[2][i][j];
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[2][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];

                    System.arraycopy(cube[0][2], 0, tmp2, 0, 3);
                    for (int i = 0; i < 3; i++) {
                        cube[0][2][i] = cube[4][2 - i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[4][i][2] = cube[1][0][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[1][0][i] = cube[5][2 - i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[5][i][0] = tmp2[i];
                    }
                } else {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[2 - j][i] = cube[2][i][j];
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[2][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];

                    System.arraycopy(cube[0][2], 0, tmp2, 0, 3);
                    for (int i = 0; i < 3; i++) {
                        cube[0][2][i] = cube[5][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[5][i][0] = cube[1][0][2 - i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[1][0][i] = cube[4][i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[4][i][2] = tmp2[2 - i];
                    }

                }
                break;
            case "B":
                if (d.equals("+")) {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[j][2 - i] = cube[3][i][j];
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[3][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];

                    System.arraycopy(cube[0][0], 0, tmp2, 0, 3);
                    for (int i = 0; i < 3; i++) {
                        cube[0][0][i] = cube[5][i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[5][i][2] = cube[1][2][2 - i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[1][2][i] = cube[4][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[4][i][0] = tmp2[2 - i];
                    }
                } else {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[2 - j][i] = cube[3][i][j];
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[3][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];

                    System.arraycopy(cube[0][0], 0, tmp2, 0, 3);
                    for (int i = 0; i < 3; i++) {
                        cube[0][0][i] = cube[4][2 - i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[4][i][0] = cube[1][2][i];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[1][2][i] = cube[5][2 - i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[5][i][2] = tmp2[i];
                    }
                }
                break;
            case "L":
                if (d.equals("+")) {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[j][2 - i] = cube[4][i][j];
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[4][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];
                    for (int i = 0; i < 3; i++)
                        tmp2[i] = cube[0][i][0];

                    for (int i = 0; i < 3; i++) {
                        cube[0][i][0] = cube[3][2 - i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[3][i][2] = cube[1][2 - i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[1][i][0] = cube[2][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[2][i][0] = tmp2[i];
                    }
                } else {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[2 - j][i] = cube[4][i][j];
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[4][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];
                    for (int i = 0; i < 3; i++) {
                        tmp2[i] = cube[0][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[0][i][0] = cube[2][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[2][i][0] = cube[1][i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[1][i][0] = cube[3][2 - i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[3][i][2] = tmp2[2 - i];
                    }
                }
                break;
            default:
                if (d.equals("+")) {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[j][2 - i] = cube[5][i][j];
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[5][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];
                    for (int i = 0; i < 3; i++) {
                        tmp2[i] = cube[0][i][2];
                    }

                    for (int i = 0; i < 3; i++) {
                        cube[0][i][2] = cube[2][i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[2][i][2] = cube[1][i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[1][i][2] = cube[3][2 - i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[3][i][0] = tmp2[2 - i];
                    }
                } else {
                    char[][] tmp = new char[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            tmp[2 - j][i] = cube[5][i][j];
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(tmp[i], 0, cube[5][i], 0, 3);
                    }

                    char[] tmp2 = new char[3];
                    for (int i = 0; i < 3; i++) {
                        tmp2[i] = cube[0][i][2];
                    }

                    for (int i = 0; i < 3; i++) {
                        cube[0][i][2] = cube[3][2 - i][0];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[3][i][0] = cube[1][2 - i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[1][i][2] = cube[2][i][2];
                    }
                    for (int i = 0; i < 3; i++) {
                        cube[2][i][2] = tmp2[i];
                    }
                }
                break;
        }

    }
}


