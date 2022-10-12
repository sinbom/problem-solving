package programmers.kakao_tech_internship_2022;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class Solution4 {

    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;

        for (int[] problem : problems) {
            maxAlp = max(maxAlp, problem[0]);
            maxCop = max(maxCop, problem[1]);
        }

        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }

        int[][] dp = new int[maxAlp + 1][maxCop + 1];

        for (int[] arr : dp) {
            fill(arr, MAX_VALUE);
        }

        alp = min(alp, maxAlp);
        cop = min(cop, maxCop);

        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[min(i + 1, maxAlp)][j] = min(dp[min(i + 1, maxAlp)][j], dp[i][j] + 1);

                dp[i][min(j + 1, maxCop)] = min(dp[i][min(j + 1, maxCop)], dp[i][j] + 1);

                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        dp[min(i + problem[2], maxAlp)][min(j + problem[3], maxCop)] = min(
                            dp[min(i + problem[2], maxAlp)][min(j + problem[3], maxCop)],
                            dp[i][j] + problem[4]
                        );
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }

    public static void main(String[] args) {
        int solution = new Solution4()
            .solution(
                0,
                0,
                new int[][]{
                    {0, 0, 2, 1, 2},
                    {4, 5, 3, 1, 2},
                    {4, 11, 4, 0, 2},
                    {10, 4, 0, 4, 2},
                }
            );

        System.out.println(solution);
    }

}
