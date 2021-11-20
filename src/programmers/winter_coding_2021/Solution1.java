package programmers.winter_coding_2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1 {

    public static class Monster implements Comparable<Monster> {

        private final String name;

        private final int exp;

        private final int expPerSecond;

        private final int index;

        public Monster(String name, int exp, int expPerSecond, int index) {
            this.name = name;
            this.exp = exp;
            this.expPerSecond = expPerSecond;
            this.index = index;
        }

        @Override
        public int compareTo(Monster o) {
            // 초당 경험치 내림차, 총 경험치 내림차, 인덱스 오름차
            int compare = Integer.compare(o.expPerSecond, this.expPerSecond);

            if (compare == 0) {
                compare = Integer.compare(o.exp, this.exp);
                if (compare == 0) {
                    compare = Integer.compare(this.index, o.index);
                }
            }

            return compare;
        }

    }

    // (공격자 공 - 수비자 방) 만큼 체력 감소
    // 0 이하인 경우는 감소 안됨
    // 플레이어가 죽거나 전투가 종료가 안되는 경우는 못잡는 것으로 판단
    public String solution(String character, String[] monsters) {
        List<Monster> answers = new ArrayList<>(monsters.length);
        String[] s = character.split(" ");
        int hp = Integer.parseInt(s[0]);
        int attack = Integer.parseInt(s[1]);
        int defense = Integer.parseInt(s[2]);

        for (int i = 0; i < monsters.length; i++) {
            s = monsters[i].split(" ");
            String monsterName = s[0];
            int monsterExp = Integer.parseInt(s[1]);
            int monsterHp = Integer.parseInt(s[2]);
            int monsterAttack = Integer.parseInt(s[3]);
            int monsterDefense = Integer.parseInt(s[4]);

            int damage = attack - monsterDefense; // 플레이어가 입히는 피해량
            int monsterDamage = monsterAttack - defense; // 몬스터가 입히는 피해량

            if (damage < 1 || (attack < monsterHp && monsterDamage >= hp)) { // 플레이어가 몬스터를 죽일 수 없거나, 몬스터가 플레이어를 죽이는 경우
                continue;
            }

            int time = monsterHp / damage; // 플레이어가 몬스터를 죽이는데 필요한 시간
            time = monsterHp % damage == 0 ? time : time + 1;

            // 죽일 수 있는 몬스터
            answers.add(
                    new Monster(
                            monsterName,
                            monsterExp,
                            monsterExp / time,
                            i
                    )
            );
        }

        Collections.sort(answers);

        return answers.get(0).name;
    }

}