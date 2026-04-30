import java.util.*;

class Solution {

    String[][] relation;
    List<Set<Integer>> candiKey = new ArrayList<>();

    public int solution(String[][] relation) {
        this.relation = relation;

        // 메인 로직
        int answer = 0;
        for (int count = 1; count <= relation[0].length; count++) {
            List<Set<Integer>> candiList = getCandiList(count);

            for (Set<Integer> c : candiList) {
                if (isCandidateKey(c)) {
                    candiKey.add(c);
                    answer += 1;
                }
            }
        }

        return answer;
    }

    private List<Set<Integer>> getCandiList(int count) {
        List<Set<Integer>> result = new ArrayList<>();
        getCombi(result, new ArrayList<Integer>(), count, 0);

        return result;
    }

    private void getCombi(List<Set<Integer>> result, List<Integer> temp, int count, int start) {
        if (temp.size() == count) {
            result.add(new HashSet<>(temp));
            return;
        }

        for (int i = start; i < relation[0].length; i++) {
            temp.add(i);
            getCombi(result, temp, count, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    private boolean isCandidateKey(Set<Integer> k) {
        // 최소성 검사
        if (k.size() >= 2) {
            for (Set<Integer> item : candiKey) {
                if (k.containsAll(item)) {
                    return false;
                }
            }
        }

        // 유일성 검사
        Set<String> keySet = new HashSet<>();
        for (String[] r : relation) {
            StringBuilder sb = new StringBuilder();
            for (int ki : k) {
                sb.append(r[ki]);
            }

            keySet.add(sb.toString());
        }

        if (keySet.size() != relation.length) {
            return false;
        }

        return true;
    }
}