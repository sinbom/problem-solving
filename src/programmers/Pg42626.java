package programmers;

import java.util.HashMap;
import java.util.Map;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/42626
 */
public class Pg42626 {

    private Map<String, String> enrollMap;

    private Map<String, Integer> amountMap;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        enrollMap = new HashMap<>(enroll.length);
        amountMap = new HashMap<>(enroll.length);

        for (int i = 0; i < enroll.length; i++) {
            enrollMap.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            go(seller[i], amount[i] * 100);
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = amountMap.get(enroll[i]);
        }

        return answer;
    }

    public void go(String name, int profit) {
        int divide = profit / 10;

        if (divide < 1) {
            amountMap.merge(name, profit, Integer::sum);
            return;
        }

        amountMap.merge(name, profit - divide, Integer::sum);

        if (name.equals("-")) {
            return;
        }

        go(enrollMap.get(name), divide);
    }

}
