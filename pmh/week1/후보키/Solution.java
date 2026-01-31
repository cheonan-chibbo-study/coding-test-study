package week1.후보키;

import java.util.*;

class Solution {
    //모든 칼럼 조합 임시 저장 리스트
    List<List<Integer>> allCombinations = new ArrayList<>();
    //최종 후보키로 확정 된 조합 저장할 리스트
    List<List<Integer>> candidateketys = new ArrayList<>();
    public int solution(String[][] relation) {
        int colSize = relation[0].length;
        //dfs 로 모든 가능한 컬럼 조합 생성
        dfs(0, colSize, new ArrayList<>());
        //길이가 짧은 순서로 정렬
        Collections.sort(allCombinations, (a, b) -> a.size() - b.size());

        for (List<Integer> comb : allCombinations) {
            if (checkMinimality(comb) && checkUniqueness(comb,relation)) {
                candidateketys.add(comb);
            }
        }


        return candidateketys.size();
    }

    public void dfs(int start, int n, List<Integer> current) {
        if (!current.isEmpty()) {
            allCombinations.add(new ArrayList<>(current));
        }

        for (int i = start; i < n; i++) {
            current.add(i);
            dfs(i + 1, n, current);
            current.remove(current.size() - 1);
        }
    }

    //최소성 검사
    private boolean checkMinimality(List<Integer> current) {
        for (List<Integer> key : candidateketys) {
            // 만약 현재 조합이 이미 존재하는 키의 모든 컬럼을 포함한다면? -> 최소성 탈락
            // 예: key=[0]인데 current=[0,1]이면, [0]만으로 식별 가능하므로 [0,1]은 최소성 위배
            if (current.containsAll(key)) {
                return  false;
            }

        }
        return true;
    }

    //유일성 검사
    private boolean checkUniqueness(List<Integer> cols, String[][] relation) {
        Set<String> set = new HashSet<>();

        int rowSize = relation.length;
        for (String[] row : relation) {
            StringBuilder sb = new StringBuilder();
            for (int idx : cols) {
                sb.append(row[idx]).append(",");
            }
            set.add(sb.toString());
        }
        // Set의 크기가 전체 행의 개수와 같다면 중복이 없다는 뜻 -> 유일성 만족
        return set.size() == rowSize;
    }
}