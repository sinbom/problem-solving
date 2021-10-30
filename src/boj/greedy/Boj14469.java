package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14469 {

    public static class Cow implements Comparable<Cow> {

        private final int appear;

        private final int wait;

        public Cow(int appear, int wait) {
            this.appear = appear;
            this.wait = wait;
        }

        @Override
        public int compareTo(Cow o) {
            return Integer.compare(this.appear, o.appear);
        }

    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int answer = 0;
            Cow[] cows = new Cow[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                cows[i] = new Cow(
                        Integer.parseInt(stringTokenizer.nextToken()),
                        Integer.parseInt(stringTokenizer.nextToken())
                );
            }
            Arrays.sort(cows);

            for (Cow cow : cows) {
                if (answer <= cow.appear) {
                    answer = cow.appear + cow.wait;
                } else {
                    answer += cow.wait;
                }
            }

            writer.write(answer + "");
        }
    }

}
