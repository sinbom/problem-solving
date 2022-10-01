package programmers.kakao_tech_internship_2022;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {

    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>(8);

        for (int i = 0; i < choices.length; i++) {
            if (choices[i] == 4) {
                continue;
            }

            char type = choices[i] < 4 ? survey[i].charAt(0) : survey[i].charAt(1);
            int point = Math.abs(choices[i] - 4);

            map.put(type, map.getOrDefault(type, 0) + point);
        }

        String[] types = {"RT", "CF", "JM", "AN"};
        StringBuilder stringBuilder = new StringBuilder();

        for (String type : types) {
            Integer left = map.getOrDefault(type.charAt(0), 0);
            Integer right = map.getOrDefault(type.charAt(1), 0);

            if (left >= right) {
                stringBuilder.append(type.charAt(0));
            } else {
                stringBuilder.append(type.charAt(1));
            }
        }

        return stringBuilder.toString();
    }

}
