package boj.greedy;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Boj1417 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
            int answer = 0;
            int current = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n - 1; i++) {
                priorityQueue.add(Integer.parseInt(reader.readLine()));
            }

            if (n == 1) {
                writer.write("0");
                return;
            }

            while (!priorityQueue.isEmpty() && priorityQueue.peek() >= current) {
                priorityQueue.add(priorityQueue.poll() - 1);
                current++;
                answer++;
            }

            writer.write(answer + "");
        }

    }
}
