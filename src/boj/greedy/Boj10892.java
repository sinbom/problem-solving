package boj.greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj10892 {

    public static class Pin implements Comparable<Pin> {

        private final int x;

        private final int y;

        private final int order;

        public Pin(int x, int y, int order) {
            this.x = x;
            this.y = y;
            this.order = order;
        }

        @Override
        public int compareTo(Pin pin) {
            int compare = Integer.compare(this.y, pin.y);

            if (compare == 0) {
                return Integer.compare(pin.x, this.x);
            }

            return compare;
        }

    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            Pin[] pins = new Pin[n * 3];

            for (int i = 0; i < pins.length; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                pins[i] = new Pin(
                        Integer.parseInt(stringTokenizer.nextToken()),
                        Integer.parseInt(stringTokenizer.nextToken()),
                        i + 1
                );
            }
            Arrays.sort(pins);

            for (int i = 0; i < pins.length; i += 3) {
                int max = pins[i].order;
                int mid = pins[i + 1].order;
                int min = pins[i + 2].order;
                int temp;

                if (max < mid) {
                    temp = max;
                    max = mid;
                    mid = temp;
                }
                if (max < min) {
                    temp = max;
                    max = min;
                    min = temp;
                }
                if (mid < min) {
                    temp = mid;
                    mid = min;
                    min = temp;
                }
                writer.write(min + " " + mid + " " + max + "\n");
            }

        }
    }

}
