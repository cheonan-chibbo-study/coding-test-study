import java.util.*;

class Solution {

    List<String[]> tList;
    List<String> paths;
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        tList = new ArrayList<>();
        for (String[] t : tickets) {
            tList.add(t);
        }

        Collections.sort(tList, (v1, v2) -> {
            if (!v1[0].equals(v2[0])) {
                return v1[0].compareTo(v2[0]);
            }

            return v1[1].compareTo(v2[1]);
        });

        // 메인 로직
        paths = new ArrayList<>();
        visited = new boolean[tList.size()];

        paths.add("ICN");
        dfs("ICN");

        String[] answer = new String[paths.size()];
        for (int i = 0; i < paths.size(); i++) {
            answer[i] = paths.get(i);
        }

        return answer;
    }

    private boolean dfs(String cur) {
        if (paths.size() == tList.size() + 1) {
            return true;
        }

        for (int i = 0; i < tList.size(); i++) {
            if (!tList.get(i)[0].equals(cur) || visited[i]) {
                continue;
            }

            String next = tList.get(i)[1];
            paths.add(next);
            visited[i] = true;

            if (dfs(next)) {
                return true;
            }

            paths.remove(paths.size() - 1);
            visited[i] = false;
        }

        return false;
    }
}