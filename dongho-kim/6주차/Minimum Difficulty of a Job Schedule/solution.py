'''
# 요구사항
d일 동안의 작업 목록을 스케줄링 해야 한다.
이때, 작업들은 의존적이다. 즉, i번 작업이 수행되려면 i번 이전의 작업들이 수행되어야 한다.

매일 최소한 한 개의 작업을 수행해야 하고,
스케줄의 난이도는 d일 동안 각 날짜의 난이도의 합계이다.
각 날짜의 난이도는 그 날에 수행된 작업 중 최고 난이도가 된다.

따라서 d일 동안 작업을 의존성을 준수하며 스케줄링을 했을 때, 각 날의 최고 난이도의 총합의 최솟값을 구하는 것이다.

# 문제 이해
[6, 5, 4, 3, 2, 1] / 2

6 / 5, 4, 3, 2, 1
6, 5 / 4, 3, 2, 1
6, 5, 4 / 3, 2, 1
6, 5, 4, 3 / 2, 1
6, 5, 4, 3, 2 / 1

---

[6, 5, 4, 3, 2, 1] / 3

6 / 5 / 4, 3, 2, 1
6 / 5, 4 / 3, 2, 1
6 / 5, 4, 3 / 2, 1
...
6, 5 / 4 / 3, 2 1

---

[11, 111, 22, 222, 33, 333, 44, 444] / 6

11 / 111 / 22 / 222 / 33 / 333, 44, 444 -> 843

# 접근 방법
1. 각 day에 일단 i번 째 작업을 하나 선택한다.
그 다음의 경우의 수는, 다음 날로 넘기거나, 다른 작업을 하나 더 선택하는 것이다. 이러한 모든 경우를 따져본다.
'''
class Solution:
    def minDifficulty(self, jobDifficulty: List[int], d: int) -> int:
        n = len(jobDifficulty)
        if n < d:
            return -1

        memo = [[[float('inf')] * 1001 for _ in range(301)] for _ in range(11)]

        # day: 현재의 날짜
        # i: 선택할 수 있는 작업의 인덱스
        # daily_max_difficulty: day날에 선택된 작업 중 가장 높은 난이도
        # total: day까지의 스케줄 난이도 총합
        def recurse(day, i, daily_max_difficulty):
            # 모든 날짜에 대해서 성공적으로 작업을 선택한 경우
            if day == d:
                if i < n:
                    return 1e9
                return 0

            # 일단 i번 날짜를 선택해본다.
            # 그런데 i가 범위를 벗어나면 고를 수 없으니, 탐색을 종료한다.
            if i >= n:
                return 1e9

            if memo[day][i][daily_max_difficulty] != float('inf'):
                return memo[day][i][daily_max_difficulty]

            result = float('inf')

            curr_job = jobDifficulty[i]
            daily_max_difficulty = max(daily_max_difficulty, curr_job)

            # 1) 다음 날로 넘긴다.
            result = min(result, recurse(day + 1, i + 1, 0) + daily_max_difficulty)

            # 2) day날에 작업을 하나 더 선택한다.
            result = min(result, recurse(day, i + 1, daily_max_difficulty))
            memo[day][i][daily_max_difficulty] = result
            return result


        return recurse(0, 0, 0)
