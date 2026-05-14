package study1.week8_9.카펫;

class Solution {
    public int[] solution(int brown, int yellow) {

        for (int row = 1; row * row <= yellow; row++) {
            if (yellow % row == 0) {
                int col = yellow / row;   // 내부 가로
                int totalRow = row + 2;   // 전체 세로
                int totalCol = col + 2;   // 전체 가로

                int brownCount = totalRow * totalCol - yellow;

                if (brownCount == brown) {
                    return new int[]{totalCol, totalRow};
                }
            }
        }

        return new int[]{0, 0};
    }
}