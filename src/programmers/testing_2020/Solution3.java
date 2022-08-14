package programmers.testing_2020;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {

    public int solution(int distance, int[][] scope, int[][] times) {
        Map<Integer, Object> map = new HashMap<>();

        for (int i = 0; i < scope.length; i++) {
            int start = Math.min(scope[i][0], scope[i][1]);
            int end = Math.max(scope[i][0], scope[i][1]);

            while (start <= end) {
                map.put(start, times[i]);
                start++;
            }
        }

        int answer = 0;

        for (int i = 1; i <= distance; i++) {
            Object o = map.get(i);

            answer++;

            if (o == null) {
                continue;
            }

            int[] time = (int[]) o;
            int value = i % (time[0] + time[1]);

            if (value > 0 && value <= time[0]) {
                return answer;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int solution = new Solution3()
            .solution(
                10,
                new int[][]{
                    {3, 4},
                    {5, 8}
                },
                new int[][]{
                    {2, 5},
                    {4, 3}
                });

        System.out.println(solution);
    }

}
