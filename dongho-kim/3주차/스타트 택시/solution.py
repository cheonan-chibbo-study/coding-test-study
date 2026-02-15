import sys
from collections import deque

input = sys.stdin.readline

n, m, oil = map(int, input().strip().split())
arr = [list(map(int, input().strip().split())) for _ in range(n)]
car_x, car_y = map(lambda it: int(it) - 1, input().strip().split())

customers = []
moved_customer = [False] * m
moved_customer_cnt = 0
for _ in range(m):
    customer_x, customer_y, goal_x, goal_y = map(lambda it: int(it) - 1, input().strip().split())
    customers.append((customer_x, customer_y, goal_x, goal_y))
customers.sort(key=lambda it: (it[0], it[1]))

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

# 현재 차 위치에서 가장 가까운 손님 위치까지 이동한 다음, 목적지까지 가는 동작을 구현한 메서드
# 이동은 반드시 빈칸으로만 이동할 수 있다.
def drive():
    global car_x, car_y, oil, moved_customer_cnt

    dist = [[-1] * n for _ in range(n)]
    q = deque()
    q.append((car_x, car_y))
    dist[car_x][car_y] = 0

    while q:
        x, y = q.popleft()
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if in_array(nx, ny) and arr[nx][ny] == 0 and dist[nx][ny] == -1:
                dist[nx][ny] = dist[x][y] + 1
                q.append((nx, ny))

    # print(*dist, sep='\n')

    target_customer_idx = -1
    min_dist = 1e9
    for i, customer in enumerate(customers):
        if moved_customer[i]:
            continue

        customer_x, customer_y, goal_x, goal_y = customer
        if dist[customer_x][customer_y] != -1 and dist[customer_x][customer_y] < min_dist:
            min_dist = dist[customer_x][customer_y]
            target_customer_idx = i

    if target_customer_idx == -1: # 다음 목표가 되는 손님을 찾지 못한 경우
        return False # 운행을 종료한다.

    target_customer = customers[target_customer_idx]
    customer_x, customer_y, goal_x, goal_y = target_customer

    # print(f'target_customer: {target_customer}')

    # 택시를 손님 위치까지 이동한다.
    oil -= min_dist
    car_x, car_y = customer_x, customer_y

    # print(f'손님 위치까지의 거리: {min_dist}')
    # print(f'손님 위치까지 이동한 후 oil: {oil}')

    # 남은 기름이 0 미만이면 운행을 종료한다.
    if oil < 0:
        return False

    # 택시를 목표 지점까지 이동한다.
    # 반환값은 이동하는 데 사용한 기름이다.
    used_oil = move_to_goal(goal_x, goal_y)
#     print(f'손님 위치에서 목적지까지 이동하는 데 사용한 기름: {used_oil}')
#     print(f'목적지까지 이동 후 남은 기름: {oil}')

    # 목적지까지 갈 수 없는 경우 운행을 종료한다.
    if used_oil == -1e9:
        return False

    # 이때 남은 oil이 0 이상이면 이동에 성공했으니 사용한 oil의 2배를 채운다.
    if oil >= 0:
        oil += (used_oil * 2)
#         print(f'이동에 성공했으니 충전한 기름: {used_oil * 2}')
#         print(f'충전 이후의 기름: {oil}')
        moved_customer_cnt += 1
#         print(f'이동에 성공한 승객 수: {moved_customer_cnt}')
#         print()
        moved_customer[target_customer_idx] = True
    else: # 남은 oil이 음수이면 운행을 종료한다.
#         print(f'이동에 실패했으니 운행을 종료한다.')
#         print()
        return False

    return True


def in_array(x, y):
    return 0 <= x < n and 0 <= y < n


# 택시를 목표 지점까지 이동한다.
# 반환값은 이동하는 데 사용한 기름이다.
def move_to_goal(goal_x: int, goal_y: int) -> int:
    global car_x, car_y, oil

    dist = [[-1] * n for _ in range(n)]
    q = deque()
    q.append((car_x, car_y))
    dist[car_x][car_y] = 0

    while q:
        x, y = q.popleft()

        if x == goal_x and y == goal_y:
            break

        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if in_array(nx, ny) and arr[nx][ny] == 0 and dist[nx][ny] == -1:
                dist[nx][ny] = dist[x][y] + 1
                q.append((nx, ny))

    if dist[goal_x][goal_y] == -1:
        return -1e9

    car_x, car_y = goal_x, goal_y
    oil -= dist[goal_x][goal_y]
    return dist[goal_x][goal_y]

def main():
    global oil

    while True:
        if not drive():
            break

        if moved_customer_cnt == m:
            break

    if moved_customer_cnt != m:
        print(-1)
    else:
        print(oil)

main()
