package programmers.winter_coding_2021;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.util.Arrays.fill;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution3 {

    private List<List<int[]>> graph;

    private int[] dp;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList<>(n + 1);
        dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }

        for (int[] path : paths) {
            graph
                .get(path[0])
                .add(new int[]{path[1], path[2]});
            graph
                .get(path[1])
                .add(new int[]{path[0], path[2]});
        }

        fill(dp, MAX_VALUE);

        bfs(gates, summits);

        int[] answer = {MAX_VALUE, MAX_VALUE};

        for (int summit : summits) {
            if (dp[summit] < answer[1]) {
                answer[0] = summit;
                answer[1] = dp[summit];
            }

            if (dp[summit] == answer[1] && summit < answer[0]) {
                answer[0] = summit;
                answer[1] = dp[summit];
            }
        }

        return answer;
    }

    private void bfs(int[] gates, int[] summits) {
        Queue<Node> queue = new LinkedList<>();

        Set<Integer> gateNumbers = Arrays
            .stream(gates)
            .boxed()
            .collect(toSet());

        Set<Integer> summitNumbers = Arrays
            .stream(summits)
            .boxed()
            .collect(toSet());

        for (int gate : gates) {
            dp[gate] = 0;
            queue.offer(Node.ofStartPoint(gate));
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (summitNumbers.contains(current.currentPoint)) {
                continue;
            }

            if (dp[current.currentPoint] < current.intensity) {
                continue;
            }

            for (int[] point : graph.get(current.currentPoint)) {
                int nextPoint = point[0];

                // 출발지는 방문 불가능
                if (gateNumbers.contains(nextPoint)) {
                    continue;
                }

                int intensity = max(dp[current.currentPoint], point[1]);

                if (intensity >= dp[nextPoint]) {
                    continue;
                }

                Node next = new Node(
                    nextPoint,
                    intensity
                );

                dp[nextPoint] = intensity;
                queue.offer(next);
            }
        }
    }

    public static class Node {

        private final int currentPoint;

        private final int intensity;

        public Node(int currentPoint, int intensity) {
            this.currentPoint = currentPoint;
            this.intensity = intensity;
        }

        public static Node ofStartPoint(int startPoint) {
            return new Node(startPoint, 0);
        }

    }

    public static void main(String[] args) {
        int[] solution = new Solution3()
            .solution(
                6,
                new int[][]{
                    {1, 2, 3},
                    {2, 3, 5},
                    {2, 4, 2},
                    {2, 5, 4},
                    {3, 4, 4},
                    {4, 5, 3},
                    {4, 6, 1},
                    {5, 6, 1}
                },
                new int[]{1, 3},
                new int[]{5}
            );

        System.out.println(solution[0]);
        System.out.println(solution[1]);
    }

}
