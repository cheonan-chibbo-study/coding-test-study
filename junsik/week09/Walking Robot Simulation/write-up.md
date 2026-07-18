# LeetCode 874. Walking Robot Simulation

## 1. 문제

로봇이 `(0, 0)`에서 북쪽을 바라본 상태로 시작한다.

명령어 배열 `commands`와 장애물의 위치를 나타내는 `obstacles`가 주어질 때, 명령을 순서대로 수행한다.

* `-2` : 왼쪽으로 90도 회전
* `-1` : 오른쪽으로 90도 회전
* `1 ~ 9` : 해당 숫자만큼 앞으로 이동

이동 중 장애물을 만나면 그 칸으로는 이동하지 않고 현재 이동 명령을 종료한다.

모든 명령을 수행하는 동안 원점으로부터의 거리의 제곱(`x² + y²`)의 최댓값을 반환하는 문제이다.

---

## 2. 문제 링크

https://leetcode.com/problems/walking-robot-simulation/

---

## 3. 문제 접근법

처음에는 DFS나 BFS를 사용할 수 있을지 생각했지만, 이 문제는 로봇의 이동 과정을 그대로 구현하는 **시뮬레이션(Simulation)** 문제라는 것을 파악하였다.

풀이 과정은 다음과 같다.

1. 장애물의 좌표를 `unordered_set`에 저장하여 빠르게 검색할 수 있도록 한다.
2. 현재 위치 `(x, y)`와 현재 방향(`dir`)을 관리한다.
3. 명령어를 하나씩 확인한다.

   * `-2`이면 왼쪽으로 회전한다.
   * `-1`이면 오른쪽으로 회전한다.
   * 양수라면 한 칸씩 이동한다.
4. 한 칸 이동할 때마다 다음 좌표를 계산한다.
5. 다음 좌표에 장애물이 있다면 현재 이동 명령을 종료한다.
6. 장애물이 없다면 실제로 이동하고 현재 위치의 거리 제곱을 계산하여 최대값을 갱신한다.
7. 모든 명령을 수행한 후 최대 거리 제곱을 반환한다.

이 문제에서 중요한 점은 여러 칸을 이동하는 명령이 주어져도 한 번에 이동하는 것이 아니라 **반드시 한 칸씩 이동하며 장애물을 확인해야 한다는 것**이다.

---

## 4. 소스코드

```cpp
class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        unordered_set<string> obstacleSet;

        for (auto& obs : obstacles) {
            obstacleSet.insert(to_string(obs[0]) + "," + to_string(obs[1]));
        }

        int dx[4] = {0, 1, 0, -1};
        int dy[4] = {1, 0, -1, 0};

        int dir = 0;
        int x = 0, y = 0;
        int maxDist = 0;

        for (int cmd : commands) {

            if (cmd == -2) {
                dir = (dir + 3) % 4;
            }
            else if (cmd == -1) {
                dir = (dir + 1) % 4;
            }
            else {
                for (int step = 0; step < cmd; step++) {

                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    string key = to_string(nx) + "," + to_string(ny);

                    if (obstacleSet.count(key))
                        break;

                    x = nx;
                    y = ny;

                    maxDist = max(maxDist, x * x + y * y);
                }
            }
        }

        return maxDist;
    }
};
```

---

## 5. 새로 알게 된 점

* 이 문제는 그래프 탐색이 아니라 현재 상태를 그대로 구현하는 시뮬레이션 문제라는 것을 알게 되었다.
* 방향을 숫자(`0~3`)로 관리하면 회전을 간단한 나머지 연산으로 처리할 수 있다.
* 여러 칸 이동하는 명령도 한 칸씩 이동해야 중간의 장애물을 올바르게 처리할 수 있다.
* `unordered_set`을 사용하면 장애물 존재 여부를 평균 `O(1)`에 확인할 수 있어 효율적으로 구현할 수 있다.
* `pair`는 좌표를 저장할 때, `unordered_set`은 특정 좌표의 존재 여부를 빠르게 확인할 때 유용하다는 점을 배웠다.

---

## 6. 느낀점

이번 문제를 통해 DFS나 BFS가 아닌 시뮬레이션 유형의 구현 방법을 연습할 수 있었다. 특히 방향 관리와 한 칸씩 이동하는 구현이 중요했고, 장애물을 효율적으로 찾기 위해 `unordered_set`을 사용하는 이유도 이해할 수 있었다. 알고리즘 자체는 어렵지 않았지만, 문제에서 요구하는 동작을 정확하게 구현하는 것이 핵심이라는 점을 배울 수 있었다.
