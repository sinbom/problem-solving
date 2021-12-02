package boj.brute_force;

import java.io.*;
import java.util.StringTokenizer;

public class Boj6603 {

    public static int[] arr;

    public static int[] answer = new int[6];

    public static int k;

    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            k = Integer.parseInt(stringTokenizer.nextToken());

            while (k != 0) {
                arr = new int[k];

                for (int i = 0; i < k; i++) {
                    arr[i] = Integer.parseInt(stringTokenizer.nextToken());
                }

                go(0, 0);

                writer.newLine();
                stringTokenizer = new StringTokenizer(reader.readLine());
                k = Integer.parseInt(stringTokenizer.nextToken());
            }

            writer.close();
        }
    }

    public static void go(int index, int count) throws IOException {
        if (count == 6) {
            for (int number : answer) {
                writer.write(number + " ");
            }
            writer.newLine();
            return;
        }

        while (index < k) {
            answer[count] = arr[index];
            index++;
            go(index, count + 1);
        }
    }

}