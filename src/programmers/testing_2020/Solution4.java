package programmers.testing_2020;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution4 {

    public int solution(int n, int[][] lighthouse) {
        List<List<Integer>> graph = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }

        for (int[] ints : lighthouse) {
            graph
                .get(ints[0])
                .add(ints[1]);
            graph
                .get(ints[1])
                .add(ints[0]);
        }

        return (int) graph
            .stream()
            .filter(nodes -> nodes.size() == 1)
            .map(nodes -> nodes.get(0))
            .distinct()
            .count();
    }

    public static void main(String[] args) {
        int solution = new Solution4()
            .solution(
                8,
                new int[][]{
                    {1, 2},
                    {1, 3},
                    {1, 4},
                    {1, 5},
                    {5, 6},
                    {5, 7},
                    {5, 8}
                });

        System.out.println(solution);
    }

}
