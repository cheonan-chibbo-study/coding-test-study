# 문제 링크
https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/

# 접근 방법
처음에는 슬라이싱 윈도우 방식으로 접근했습니다.    
`words`는 `itertools`의 `permutations` 돌려서 모든 후보군을 뽑아 집합으로 저장합니다. (`candidates = set(map(lambda it: ''.join(it), list(permutations(words, len(words)))))`)  
이후 `l, r = 0, len(words[0]) * len(words)` 로 잡고 조건에 해당하지 않으면 윈도우를 우측으로 한 칸 옮깁니다.

하지만 메모리 초과가 발생했습니다.

그래서 다른 방식을 떠올렸습니다. 아무래도 `candidates` 를 저장하는 부분에서 메모리 초과가 발생한 것 같았습니다.  
개선 방법을 고민하기 위해 문제 풀이 흐름을 다시 파악해보니, 굳이 슬라이싱 윈도우가 아니고 1중 for문으로 `s`를 순회하면서 `l`과 `r`을 설정하고, l부터 r까지 `len(words[0])` 만큼 `substr`을 만든 다음, `words`에 등장한 문자가 모두 등장했는 지 개수를 세는 방식으로 구현해도 될 것 같았습니다.  
이 방식은 `candidates`를 저장하지 않고 `words`에 등장한 모든 `word`의 개수를 한번만 기록하면 되기에 메모리를 효율적으로 사용할 수 있게 됩니다.

1중 for문으로 `l`을 지정하고, 탐색할 끝 범위를 `r`로 할당한 다음, l부터 r까지 `len(words[0])` 만큼 반복하면서 `substr`을 추출합니다.  
그리고 각 `substr`의 등장 횟수를 기록한 다음, 만족해야 하는 조건을 만족하는 지 판별합니다.  
만족한다면 `l`의 위치를 `answer` 리스트에 저장합니다.

# 배운 점
1. 다양한 방식으로 문제를 풀 수 있다.
2. 요구사항이랑 접근 방법을 대충 훑고 바로 코드를 작성해서 방법을 많이 헤맨 것 같다.
