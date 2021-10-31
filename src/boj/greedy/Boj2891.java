package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2891 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int r = Integer.parseInt(stringTokenizer.nextToken());
            int[] ss = new int[s];
            boolean[] check = new boolean[n + 1];
            int answer = 0;

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < s; i++) {
                ss[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < r; i++) {
                check[Integer.parseInt(stringTokenizer.nextToken())] = true;
            }

            // 자기 팀의 배가 부서진 경우지만 여유분이 있는 경우는 반드시 자기가 사용해야한다.
            // ex s = [1, 2, 3]   r = [2, 3, 4]인 경우
            // 자기 자신의 여유분을 사용하면 s = [1]   r = [4]이지만
            // 미리 지우지 않는 경우, 오른쪽 팀의 배를 사용하게 되면서 s = [], r = []이 되어 갯수가 다르게 나온다.
            ss = Arrays
                    .stream(ss)
                    .filter(number -> {
                        boolean has = check[number];
                        if (has) {
                            check[number] = false;
                        }
                        return !has;
                    })
                    .sorted()
                    .toArray();

            for (Integer number : ss) {
                if (number > 1 && check[number - 1]) {
                    check[number - 1] = false;
                } else if (number < n && check[number + 1]) {
                    check[number + 1] = false;
                } else {
                    answer++;
                }
            }

            writer.write(answer + "");
        }
    }

}
