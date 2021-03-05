def find(m, n, board):
    visited = [[False]*n for _ in range(m)]
    findflag = False
    for i in range(m-1):
        for j in range(n-1):
            if(board[i][j] == '0'):
                continue
            new_board = []
            if(board[i][j] not in new_board):
                new_board.append(board[i][j])
            if(board[i][j+1] not in new_board):
                new_board.append(board[i][j+1])
            if(board[i+1][j] not in new_board):
                new_board.append(board[i+1][j])
            if(board[i+1][j+1] not in new_board):
                new_board.append(board[i+1][j+1])
            if(len(new_board)==1):
                findflag = True
                visited[i][j] = True
                visited[i][j+1] = True
                visited[i+1][j] = True
                visited[i+1][j+1] = True
    
    return visited, findflag
def deleteboard(visited, board, m, n):
    delete_num = 0
    for i in range(m):
        for j in range(n):
            if(visited[i][j]):
                delete_num+=1
                board[i][j] = '0'
    return board, delete_num
def downboard(board, m, n):
    for i in range(n):
        check_board = []
        for j in range(m):
            check_board.append(board[j][i])
        zero_count = check_board.count('0')
        for j in range(zero_count):
            board[j][i] = '0'
            check_board.remove('0')
        for j in range(zero_count, m):
            board[j][i] = check_board[j-zero_count]
    return board
def solution(m, n, board):
    answer = 0
    for i in range(m):
        board[i] = list(board[i])
    while(True):
        visited, findflag = find(m, n, board)
        if(findflag == False):
            break
        board, delete_num = deleteboard(visited, board, m, n)
        answer+=delete_num
        board = downboard(board, m, n)
    return answer

if __name__ == "__main__":
    print(solution(4, 5, ["CCBDE", "AAADE", "AAABF", "CCBBF"]))
    print(solution(6, 6, ["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]))