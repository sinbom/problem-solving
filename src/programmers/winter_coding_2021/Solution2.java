package programmers.winter_coding_2021;

public class Solution2 {

    public static int max = 0;

    public int solution(int time, int gold, int[][] upgrade) {
        go(time, gold, 0, 0, upgrade);
        return max;
    }

    public void go(int time, int gold, int level, int money, int[][] upgrade) {
        // 다음 레벨로 안가는 경우
        int count = time / upgrade[level][1];
        max = Math.max(max, money + count * gold);

        // 다음 레벨로 가는 경우
        if (level + 1 < upgrade.length) {
            if (money >= upgrade[level + 1][0]) { // 바로 업그레이드 가능
                go(
                        time,
                        gold,
                        level + 1,
                        money - upgrade[level + 1][0],
                        upgrade
                );
            } else { // 바로 업그레이드 불가능
                int need = (upgrade[level + 1][0] - money) / gold;
                need = (upgrade[level + 1][0] - money) % gold == 0 ? need : need + 1;

                int needTime = need * upgrade[level][1];

                if (time <= needTime) { // 주어진 시간보다 업그레이드를 위해 광석을 캐는 시간이 많은 경우, 업그레이드 불가
                    return;
                }

                go(
                        time - needTime,
                        gold,
                        level + 1,
                        (money + need * gold) - upgrade[level + 1][0],
                        upgrade
                );
            }
        }
    }

}