class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        visited = [False] * len(rooms)
        self.dfs(0, rooms, visited)

        for v in visited:
            if not v:
                return False
        return True

    def dfs(self, number, rooms, visited):
        visited[number] = True

        keys = rooms[number]
        for key in keys:
            if visited[key]: continue
            self.dfs(key, rooms, visited)
