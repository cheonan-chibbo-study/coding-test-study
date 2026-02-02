class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        # 전역 데이터
        DIR = [[-1, 0], [0, 1], [0, -1], [1, 0], [-1, 1], [-1, -1], [1, 1], [1, -1]]  # 북, 동, 서, 남, 북동, 북서, 남동, 남서

        # 메서드
        def back_tracking(start_row, start_col, result, visited, step):
            if (step > n):
                result.append(convert_visited_to_result(visited))
                return
            
            for row in range(start_row, n):
                start_col = start_col if row == start_row else 0
                for col in range(start_col, n):
                    if (not is_safe(row, col, visited)):
                        continue
                    
                    visited[row][col] = True
                    next_col = (col + 1) % n
                    next_row = row + 1 if (next_col == 0) else row
                    back_tracking(next_row, next_col, result, visited, step + 1)

                    visited[row][col] = False
        
        def is_safe(row, col, visited):
            for d in DIR:
                next_row = row + d[0]
                next_col = col + d[1]

                while (next_row >= 0 and next_row < n and next_col >= 0 and next_col < n):
                    if (visited[next_row][next_col]):
                        return False
                    
                    next_row += d[0]
                    next_col += d[1]
            
            return True
        
        def convert_visited_to_result(visited):
            result = []
            for row in visited:
                row_str = ""
                for col in row:
                    row_str += "Q" if (col) else "."
                result.append(row_str)
            
            return result

        # 메인 로직
        result = []
        visited = [[False for _ in range(n)] for _ in range(n)]
        back_tracking(0, 0, result, visited, 1)

        return result
