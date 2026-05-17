import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {
        Deque<int[]> dq = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        dq.offer(new int[]{amount, 0});

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            if (cur[0] == 0) {
                return cur[1];
            }

            for (int coin : coins) {
                int nextAmount = cur[0] - coin;

                if (nextAmount < 0 || visited.contains(nextAmount)) {
                    continue;
                }

                dq.offer(new int[]{nextAmount, cur[1] + 1});
                visited.add(nextAmount);
            }
        }

        return -1;
    }
}