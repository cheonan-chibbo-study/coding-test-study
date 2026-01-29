class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        # 상수
        DIR = [[-1, 0], [0, 1], [0, -1], [1, 0]]  # 상, 우, 좌, 하
        M = len(board)
        N = len(board[0])

        # 메서드
        def search(row, col, visited, step):
            if step == len(word):
                return True
            
            for dx, dy in DIR:
                nextRow = row + dx
                nextCol = col + dy

                if not isSafe(nextRow, nextCol) or visited[nextRow][nextCol] or board[nextRow][nextCol] != word[step]:
                    continue

                visited[nextRow][nextCol] = True
                if search(nextRow, nextCol, visited, step + 1):
                    return True
                
                visited[nextRow][nextCol] = False
            
            return False
        
        def isSafe(row, col):
            return row >= 0 and row < M and col >= 0 and col < N

        # 메인 로직
        for row in range(M):
            for col in range(N):
                if board[row][col] != word[0]:
                    continue
                
                visited = [[False for _ in range(N)] for _ in range(M)]
                visited[row][col] = True
                if search(row, col, visited, 1):
                    return True
        
        return False
