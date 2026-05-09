from collections import deque

class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        visited = [False] * len(rooms)
        dq = deque([0])
        visited[0] = True

        while dq:
            cur = dq.popleft()
            for next in rooms[cur]:
                if visited[next]:
                    continue

                dq.append(next)
                visited[next] = True

        return not False in visited