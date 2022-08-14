package programmers.testing_2020;

import java.util.Stack;

public class Solution2 {

    private static final int BREAD = 1;

    private static final int VEGETABLE = 2;

    private static final int MEAT = 3;

    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i : ingredient) {
            if (i == BREAD && stack.size() > 2) {
                Integer first = stack.pop();
                Integer second = stack.pop();
                Integer third = stack.pop();

                if (first == MEAT && second == VEGETABLE && third == BREAD) {
                    answer++;
                } else {
                    stack.add(third);
                    stack.add(second);
                    stack.add(first);
                    stack.add(i);
                }
            } else {
                stack.add(i);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int solution = new Solution2()
            .solution(new int[]{2, 1, 1, 2, 3, 1, 2, 3, 1});
//            .solution(new int[]{1,2,3,1,1,2,3,1,1, 2,3,1,2,3,1});

        System.out.println(solution);
    }

}
