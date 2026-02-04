package week1.메뉴리뉴얼;
import java.util.*;

class Solution {
    /*
    코스 요리 메뉴는 최소 2가지이상 단품메뉴
    2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함

    손님들이 주문한 단품메뉴 orders
    스키피 가 추가하고싶어하는 코스요리를 구성하는 단품메뉴들의 갯수가 담긴 배열 course

  1번 손님이 주문한 단품메뉴 조합 다만들고 저장
  2번 손님이 주문한 단품메뉴 조합 만들고 1번 조합이랑 비교해서 해당 코스에 +1

    */
    public String[] solution(String[] orders, int[] course) {


        List<Map<String,Integer>> list = new ArrayList<>();
        for(int i=0;i<=10;i++){
            list.add(new HashMap<>());
        }
        //코스 요리탐색
        for(int i=0 ; i<course.length;i++){
            //코스 요리 개수
            int n = course[i];
            for(int j=0;j<orders.length;j++){

                //주문 메뉴 확인
                if(n <= orders[j].length()){
                    //주문 메뉴 정렬
                    char[] c = orders[j].toCharArray();

                    //손님 A 가 XYZ 시키고 B 가 ZYX 일때 정렬 안하면
                    // xy yx 가 같은거로 취급 안되고 다른걸로 취급되서 저장됨 이걸 방지하기위해 서 정렬
                    Arrays.sort(c);

                    //주문 메뉴의 알파벳 경우의 수 구하기
                    backtrace(list.get(n),c,"",n,0,new boolean[c.length]);

                }
            }

        }
        //정답 리스트
        List<String> answer = new ArrayList<>();

        for(Map<String,Integer> map : list){
            //코스 요리가 있는경우
            if(map.size() >0){
                int max = -1;

                for(String key:map.keySet()){
                    if(map.get(key)>1) //최소 2명 이상 주문해야됨
                    {
                        max = Math.max(max,map.get(key));
                    }
                }

                //최대값과 동일하면, 정답 리스트에 코스 요리추가
                for(String key:map.keySet()){
                    if(max == map.get(key)){
                        answer.add(key);
                    }

                }
            }


        }
        Collections.sort(answer);
        //정답 리스트 배열로 변환
        return answer.stream().toArray(String[]::new);
    }


    //주문 메뉴 알파벳 조합 구하기
    public void backtrace(Map<String,Integer> map, char[] arr, String str, int n, int pos, boolean[] visited){

        if(str.length() == n){
            //map 에서 str(찾으려는 키)의 값을 가져오되, 만약 그키가 없다면 기본값으로 0 을반환
            //
            map.put(str,map.getOrDefault(str,0)+1);
            return;
        }

        for(int i = pos;i<arr.length;i++){
            if(visited[i])continue;
            visited[i] =true;
            backtrace(map,arr,str+String.valueOf(arr[i]),n,i+1,visited);
            visited[i] =false;
        }



    }
}