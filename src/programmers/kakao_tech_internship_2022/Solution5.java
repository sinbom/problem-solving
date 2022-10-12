package programmers.kakao_tech_internship_2022;

import java.util.LinkedList;

public class Solution5 {

    LinkedList<LinkedList<Integer>> rows;

    LinkedList<Integer> left;

    LinkedList<Integer> right;

    public int[][] solution(int[][] rc, String[] operations) {
        rows = new LinkedList<>();
        left = new LinkedList<>();
        right = new LinkedList<>();

        for (int[] arr : rc) {
            LinkedList<Integer> row = new LinkedList<>();

            for (int j = 1; j < arr.length - 1; j++) {
                row.add(arr[j]);
            }

            rows.add(row);
        }

        for (int[] arr : rc) {
            left.add(arr[0]);
            right.add(arr[rc[0].length - 1]);
        }



        for (String operation : operations) {
            if ("Rotate".equals(operation)) {
                rotate();
            } else {
                shiftRow();
            }
        }

        for (int i = 0; i < rc.length; i++) {
            LinkedList<Integer> row = rows.poll();

            for (int j = 1; j < rc[0].length - 1; j++) {
                rc[i][j] = row.poll();
            }
        }

        for (int i = 0; i < rc.length; i++) {
            rc[i][0] = left.poll();
            rc[i][rc[0].length - 1] = right.poll();
        }

        return rc;
    }

    private void rotate() {
        rows.peekFirst().addFirst(left.pollFirst());
        right.addFirst(rows.peekFirst().pollLast());
        rows.peekLast().addLast(right.pollLast());
        left.addLast(rows.peekLast().pollFirst());
    }

    private void shiftRow() {
        rows.addFirst(rows.pollLast());
        left.addFirst(left.pollLast());
        right.addFirst(right.pollLast());
    }

    public static void main(String[] args) {
        int[][] solution = new Solution5()
            .solution(
//                new int[][]{
//                    {1, 2, 3},
//                    {4, 5, 6},
//                    {7, 8, 9}
//                },
                new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12}
                },
                new String[]{"ShiftRow", "Rotate", "ShiftRow", "Rotate"}
            );

        for (int[] ints : solution) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

}
