import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] path : paths) {
            graph.computeIfAbsent(path[0], key -> new ArrayList<>()).add(new int[]{path[1], path[2]});
            graph.computeIfAbsent(path[1], key -> new ArrayList<>()).add(new int[]{path[0], path[2]});
        }

        // 메인 로직
        int[] intensities = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            intensities[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Item> pq = new PriorityQueue<>((v1, v2) -> {
            return Integer.compare(v1.intensity, v2.intensity);
        });

        for (int gate : gates) {
            pq.offer(new Item(gate, 0));
            intensities[gate] = 0;
        }

        while (!pq.isEmpty()) {
            Item cur = pq.poll();

            if (
                cur.intensity > intensities[cur.vertex] ||
                    summitSet.contains(cur.vertex)
            ) {
                continue;
            }

            for (int[] next : graph.computeIfAbsent(cur.vertex, key -> new ArrayList<>())) {
                int nextIntensity = Math.max(next[1], cur.intensity);

                if (nextIntensity < intensities[next[0]]) {
                    pq.offer(new Item(next[0], nextIntensity));
                    intensities[next[0]] = nextIntensity;
                }
            }
        }

        List<Integer> summitList = new ArrayList<>(summitSet);
        Collections.sort(summitList);

        int[] answer = new int[]{-1, Integer.MAX_VALUE};
        for (int summit : summitList) {
            if (intensities[summit] < answer[1]) {
                answer = new int[]{summit, intensities[summit]};
            }
        }

        return answer;
    }

    class Item {
        int vertex;
        int intensity;

        public Item(int vertex, int intensity) {
            this.vertex = vertex;
            this.intensity = intensity;
        }
    }
}