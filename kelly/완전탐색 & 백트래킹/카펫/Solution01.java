class Solution {
    public int[] solution(int brown, int yellow) {
        int area = brown + yellow;

        for (int h = 3; h <= (int) Math.pow(area, 0.5); h++) {
            if (area % h != 0) {
                continue;
            }

            int w = area / h;
            if ((w - 2) * (h - 2) == yellow) {
                return new int[]{w, h};
            }
        }

        return new int[]{-1, -1};
    }
}