class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        visited = [False] * len(rooms)
        stack = [0]
        visited[0] = True

        while stack:
            cur = stack.pop()
            for next in rooms[cur]:
                if visited[next]:
                    continue

                stack.append(next)
                visited[next] = True

        return not False in visited