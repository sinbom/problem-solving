package programmers.kakao_tech_internship_2022;

public class Solution1 {

    public int solution(int[] queue1, int[] queue2) {
        int[] arr = new int[queue1.length + queue2.length];

        int index = 0;
        long arrSum = 0;
        long rangeSum = 0;

        for (int value : queue1) {
            arr[index++] = value;
            arrSum += value;
            rangeSum += value;
        }

        for (int value : queue2) {
            arr[index++] = value;
            arrSum += value;
        }

        if (arrSum % 2 != 0) {
            return -1;
        }

        long halfOfArrSum = arrSum / 2;
        int left = 0;
        int right = queue1.length - 1;
        int count = 0;

        while (rangeSum != halfOfArrSum) {
            if (rangeSum > halfOfArrSum) {
                if (left < right) {
                    rangeSum -= arr[left];
                    left++;
                } else {
                    if (right < arr.length - 2) {
                        right++;
                        rangeSum += arr[right];
                    } else {
                        return -1;
                    }
                }
            } else {
                if (right < arr.length - 2) {
                    right++;
                    rangeSum += arr[right];
                } else {
                    return -1;
                }
            }

            count++;
        }

        return count;
    }

}
