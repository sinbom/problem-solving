package programmers.testing_2020;

public class Solution1 {

    public int solution(int a, int b, int n) {
        int answer = 0;
        int modular = 0;

        while (n >= a || modular + n >= a) {
            if (n < a) {
                n += modular;
                modular = 0;
            }

            modular += n % a;
            n = n / a * b;
            answer += n;
        }

        return answer;
    }

    public static void main(String[] args) {
        int solution = new Solution1()
            .solution(2, 1, 20);

        System.out.println(solution);
    }

    // 빈병 2개 -> 콜라 1병

    // 20 -> 10병
    // 받은 10 마시고 -> 5병
    // 받은 5명 마시고 -> 2병
    // 받은 2병 마시고 -> 1병
    // 남은 2병 -> 1병
    // 10 + 5 + 2 + 1 + 1 = 19병

}
