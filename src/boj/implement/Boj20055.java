package boj.implement;

import java.io.*;
import java.util.StringTokenizer;

public class Boj20055 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int length = 2 * n;
            int[] durability = new int[length];
            boolean[] check = new boolean[length];
            int head = 0;
            int tail = n - 1;
            int answer = 0;

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < length; i++) {
                durability[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            for (int count = 0; count < k; answer++) {
                // step 1, 벨트 회전
                head = (head - 1 + length) % length;
                tail = (tail - 1 + length) % length;
                check[tail] = false;

                // step 2, 가장 먼저 올라간 로봇부터 앞에 로봇이 없거나, 내구도가 1이상이면 한 칸 이동
                for (int i = (tail - 1 + length) % length; i != (head - 1 + length) % length; i = (i - 1 + length) % length) {
                    if (check[i] && !check[(i + 1) % length] && durability[(i + 1) % length] > 0) {
                        check[i] = false;
                        check[(i + 1) % length] = true;
                        durability[(i + 1) % length]--;

                        if (durability[(i + 1) % length] == 0) {
                            count++;
                        }
                    }
                }
                check[tail] = false;

                // step 3, 올리는 위치에 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다
                if (!check[head] && durability[head] > 0) {
                    check[head] = true;
                    durability[head]--;

                    if (durability[head] == 0) {
                        count++;
                    }
                }
            }

            writer.write(answer + "");
        }
    }

}