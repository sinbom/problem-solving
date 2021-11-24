package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj20327 {

    public static int[][] arr;

    public static int length;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int r = Integer.parseInt(stringTokenizer.nextToken());
            length = 1 << n;
            arr = new int[length][length];

            for (int i = 0; i < length; i++) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < length; j++) {
                    arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            while (r-- > 0) {
                stringTokenizer = new StringTokenizer(reader.readLine());
                int k = Integer.parseInt(stringTokenizer.nextToken());
                int l = Integer.parseInt(stringTokenizer.nextToken());
                int s = 1 << l;

                if (k == 1) {
                    operate1(s);
                } else if (k == 2) {
                    operate2(s);
                } else if (k == 3) {
                    operate3(s);
                } else if (k == 4) {
                    operate4(s);
                } else if (k == 5) {
                    operate5(s);
                } else if (k == 6) {
                    operate6(s);
                } else if (k == 7) {
                    operate7(s);
                } else {
                    operate8(s);
                }
            }

            StringBuilder stringBuilder = new StringBuilder();

            for (int[] ints : arr) {
                for (int anInt : ints) {
                    stringBuilder.append(anInt).append(' ');
                }
                stringBuilder.append('\n');
            }

            writer.write(stringBuilder.toString());
        }
    }

    public static void operate1(int s) {
        for (int i = 0; i < length; i += s) {
            for (int j = 0; j < length; j += s) {
                for (int k = i; k < i + s / 2; k++) {
                    for (int l = j; l < j + s; l++) {
                        int temp = arr[k][l];
                        int index = k + (s - 1 - (k - i) * 2);

                        arr[k][l] = arr[index][l];
                        arr[index][l] = temp;
                    }
                }
            }
        }
    }

    public static void operate2(int s) {
        for (int i = 0; i < length; i += s) {
            for (int j = 0; j < length; j += s) {
                for (int k = i; k < i + s; k++) {
                    for (int l = j; l < j + s / 2; l++) {
                        int temp = arr[k][l];
                        int index = l + (s - 1 - (l - j) * 2);

                        arr[k][l] = arr[k][index];
                        arr[k][index] = temp;
                    }
                }
            }
        }
    }

    public static void operate3(int s) {
        for (int i = 0; i < length; i += s) {
            for (int j = 0; j < length; j += s) {
                int[][] temp = new int[s][s];

                for (int k = i; k < i + s; k++) {
                    for (int l = j; l < j + s; l++) {
                        temp[l - j][s - 1 - (k - i)] = arr[k][l];
                    }
                }
                for (int k = 0; k < s; k++) {
                    for (int l = 0; l < s; l++) {
                        arr[k + i][l + j] = temp[k][l];
                    }
                }
            }
        }
    }

    public static void operate4(int s) {
        for (int i = 0; i < length; i += s) {
            for (int j = 0; j < length; j += s) {
                int[][] temp = new int[s][s];

                for (int k = i; k < i + s; k++) {
                    for (int l = j; l < j + s; l++) {
                        temp[s - 1 - (l - j)][k - i] = arr[k][l];
                    }
                }
                for (int k = 0; k < s; k++) {
                    for (int l = 0; l < s; l++) {
                        arr[k + i][l + j] = temp[k][l];
                    }
                }
            }
        }
    }

    public static void operate5(int s) {
        operate1(length);
        operate1(s);
    }

    public static void operate6(int s) {
        operate2(length);
        operate2(s);
    }

    public static void operate7(int s) {
        operate3(length);
        operate4(s);
    }

    public static void operate8(int s) {
        operate4(length);
        operate3(s);
    }

}