package week8.행렬과연산;
import java.util.*;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        int r = rc.length;
        int c = rc[0].length;

        Deque<Integer> left = new ArrayDeque<>();
        Deque<Integer> right = new ArrayDeque<>();
        Deque<Deque<Integer>> middle = new ArrayDeque<>();

        // left, middle, right 분리
        for (int i = 0; i < r; i++) {
            left.offerLast(rc[i][0]);
            right.offerLast(rc[i][c - 1]);

            Deque<Integer> rowMid = new ArrayDeque<>();
            for (int j = 1; j <= c - 2; j++) {
                rowMid.offerLast(rc[i][j]);
            }
            middle.offerLast(rowMid);
        }

        for (String op : operations) {
            if (op.equals("ShiftRow")) {
                shiftRow(left, middle, right);
            } else {
                rotate(left, middle, right);
            }
        }

        // 다시 2차원 배열로 복원
        int[][] answer = new int[r][c];

        for (int i = 0; i < r; i++) {
            Deque<Integer> rowMid = middle.pollFirst();

            answer[i][0] = left.pollFirst();

            for (int j = 1; j <= c - 2; j++) {
                answer[i][j] = rowMid.pollFirst();
            }

            answer[i][c - 1] = right.pollFirst();
        }

        return answer;
    }

    private void shiftRow(Deque<Integer> left,
                          Deque<Deque<Integer>> middle,
                          Deque<Integer> right) {
        left.offerFirst(left.pollLast());
        middle.offerFirst(middle.pollLast());
        right.offerFirst(right.pollLast());
    }

    private void rotate(Deque<Integer> left,
                        Deque<Deque<Integer>> middle,
                        Deque<Integer> right) {

        // 열이 2개 이상일 때 일반 처리
        Deque<Integer> topMid = middle.peekFirst();
        Deque<Integer> bottomMid = middle.peekLast();

        // 1) left 맨 위 -> top middle 맨 앞
        topMid.offerFirst(left.pollFirst());

        // 2) top middle 맨 뒤 -> right 맨 위
        right.offerFirst(topMid.pollLast());

        // 3) right 맨 아래 -> bottom middle 맨 뒤
        bottomMid.offerLast(right.pollLast());

        // 4) bottom middle 맨 앞 -> left 맨 아래
        left.offerLast(bottomMid.pollFirst());
    }
}