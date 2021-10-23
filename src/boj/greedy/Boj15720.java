package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj15720 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            Integer[] burgers = new Integer[b];
            Integer[] sides = new Integer[c];
            Integer[] drinks = new Integer[d];
            int setCount = Math.min(Math.min(b, c), d);
            int sum = 0;
            int answer = 0;

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < burgers.length; i++) {
                burgers[i] = Integer.parseInt(stringTokenizer.nextToken());
                sum += burgers[i];
            }

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < sides.length; i++) {
                sides[i] = Integer.parseInt(stringTokenizer.nextToken());
                sum += sides[i];
            }

            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < drinks.length; i++) {
                drinks[i] = Integer.parseInt(stringTokenizer.nextToken());
                sum += drinks[i];
            }

            Arrays.sort(burgers, Comparator.reverseOrder());
            Arrays.sort(sides, Comparator.reverseOrder());
            Arrays.sort(drinks, Comparator.reverseOrder());

            for (int i = 0; i < setCount; i++) {
                int price = burgers[i] + sides[i] + drinks[i];
                answer += price - price / 10;
            }

            for (int i = setCount; i < burgers.length; i++) {
                answer += burgers[i];
            }

            for (int i = setCount; i < sides.length; i++) {
                answer += sides[i];
            }

            for (int i = setCount; i < drinks.length; i++) {
                answer += drinks[i];
            }

            writer.write(sum + "");
            writer.newLine();
            writer.write(answer + "");
            writer.newLine();
        }
    }

}
