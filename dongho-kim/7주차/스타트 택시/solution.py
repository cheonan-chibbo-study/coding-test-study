import sys
from collections import deque

input = sys.stdin.readline

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

n, m, fuel = map(int, input().strip().split())
arr = [list(map(int, input().strip().split())) for _ in range(n)]
curr_pos = tuple(map(lambda x: int(x) - 1, input().strip().split()))

def in_array(x, y):
    return 0 <= x < n and 0 <= y < n
def is_wall(x, y):
    return arr[x][y] == 1

def get_min_dist(start_x, start_y, target_x, target_y):
    q = deque()
    visited = [[False] * n for _ in range(n)]

    q.append((start_x, start_y, 0))
    visited[start_x][start_y] = True

    while q:
        x, y, dist = q.popleft()

        if x == target_x and y == target_y:
            return dist

        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]

            if not in_array(nx, ny): continue
            if is_wall(nx, ny): continue
            if visited[nx][ny]: continue

            q.append((nx, ny, dist + 1))
            visited[nx][ny] = True

    return -1

# 각 손님의 위치에서 목적지까지의 거리를 미리 기록해놓는다.
clients = []
for _ in range(m):
    client_x, client_y, target_x, target_y = list(map(lambda x: int(x) - 1, input().strip().split()))
    min_dist = get_min_dist(client_x, client_y, target_x, target_y)

    # -1이 반환됐다는 것은 승객이 목적지까지 갈 수 없다는 뜻이다.
    if min_dist == -1:
        print(-1)
        exit()
    clients.append([client_x, client_y, target_x, target_y, min_dist])

# moved[i] : i번 승객이 목적지까지 이동했는지 여부
moved = [False] * m
moved_cnt = 0

def find_min_dist_client_idx():
    dist = [[0] * n for _ in range(n)]
    q = deque()
    visited = [[False] * n for _ in range(n)]

    q.append((curr_pos[0], curr_pos[1]))
    visited[curr_pos[0]][curr_pos[1]] = True

    while q:
        x, y = q.popleft()

        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]

            if not in_array(nx, ny): continue
            if is_wall(nx, ny): continue
            if visited[nx][ny]: continue

            q.append((nx, ny))
            visited[nx][ny] = True
            dist[nx][ny] = dist[x][y] + 1

    min_dist_to_client = float('inf')
    min_client_idx = -1
    for i in range(m):
        if moved[i]: continue
        client_x, client_y, target_x, target_y, min_dist = clients[i]

        if not visited[client_x][client_y]: continue

        client_dist = dist[client_x][client_y]
        if client_dist < min_dist_to_client:
            min_dist_to_client = client_dist
            min_client_idx = i
        elif client_dist == min_dist_to_client:
            prev_x, prev_y, _, _, _ = clients[min_client_idx]
            if (client_x, client_y) < (prev_x, prev_y):
                min_client_idx = i

    return (min_client_idx, min_dist_to_client)


while True:
    curr_x, curr_y = curr_pos
    # print(f'curr_x: {curr_x}, curr_y: {curr_y}')

    # 현재 택시 위치에서 가장 최단거리의 승객을 찾는다.
    min_dist_client_idx, min_dist_to_client = find_min_dist_client_idx()
    if min_dist_client_idx == -1:
        print(-1)
        exit()
    client = clients[min_dist_client_idx]
    client_x, client_y, target_x, target_y, min_dist = client
    # print(f'min_dist_to_client: {min_dist_to_client}, min_client_idx: {min_client_idx}')

    # 연료를 차감한다.
    fuel -= min_dist_to_client
    # print(f'승객까지 이동 후 fuel: {fuel}')

    # 남은 연료가 0 미만이면 이동에 실패한 것이니 운행을 종료한다.
    if fuel < 0:
        print(-1)
        exit()

    # 승객 위치부터 목적지까지의 거리(min_dist)만큼 연료를 차감한다.
    fuel -= min_dist
    # print(f'목적지까지 이동 후 fuel: {fuel}')

    # 남은 연료가 0 미만이면 이동에 실패한 것이므로 운행을 종료한다.
    if fuel < 0:
        print(-1)
        exit()

    # 목적지까지의 이동거리(min_dist)의 2배를 더한다.
    fuel += (min_dist * 2)

    # 택시 위치를 목적지로 갱신하고 운행 횟수를 카운트한다.
    curr_pos = (target_x, target_y)
    moved[min_dist_client_idx] = True
    moved_cnt += 1

    # 운행 횟수가 M이면 운행을 종료한다.
    if moved_cnt == m:
        print(fuel)
        break
