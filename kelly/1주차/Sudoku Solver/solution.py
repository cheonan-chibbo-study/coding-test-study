class Solution:
    def solveSudoku(self, board):
        # 전역 데이터
        EMPTY_STR = "."

        # 메서드
        def back_tracking(step, total_empty_count):
            if (step == total_empty_count):
                return True
            
            for row in range(len(board)):
                for col in range(len(board[row])):
                    if (board[row][col] != EMPTY_STR):
                        continue
                    
                    for candi in range(1, 10):
                        if (not is_safe(row, col, candi)):
                            continue
                        
                        board[row][col] = str(candi)
                        if (back_tracking(step + 1, total_empty_count)):
                            return True

                        board[row][col] = EMPTY_STR
                        
                    return False
            
            return True
        
        def is_safe(target_row, target_col, target):
            for col in range(len(board)):
                if (col == target_col):
                    continue
                
                if (board[target_row][col] == str(target)):
                    return False
            
            for row in range(len(board)):
                if (row == target_row):
                    continue
                
                if (board[row][target_col] == str(target)):
                    return False
            
            start_row = target_row - (target_row % 3)
            start_col = target_col - (target_col % 3)
            for row in range(start_row, start_row + 3):
                for col in range(start_col, start_col + 3):
                    if (row == target_row and col == target_col):
                        continue
                    
                    if (board[row][col] == str(target)):
                        return False
            
            return True

        # 메인 로직
        total_empty_count = 0
        for row in board:
            for col in row:
                if (col == EMPTY_STR):
                    total_empty_count += 1

        back_tracking(0, total_empty_count)