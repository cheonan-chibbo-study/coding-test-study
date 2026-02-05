from collections import deque

class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        queue = deque([0])
        visited = [False for _ in rooms]
        visited[0] = True

        while queue:
            cur = queue.popleft()
            for nxt in rooms[cur]:
                if not visited[nxt]:
                    visited[nxt] = True
                    queue.append(nxt)

        return all(visited)