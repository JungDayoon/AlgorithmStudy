T = int(input())
for tc in range(T):
    n = int(input())
    board = [list(map(int, input().split())) for _ in range(2)]
    board[0][1] += board[1][0]
    board[1][1] += board[0][0]

    for j in range(2, n):
        board[0][j] += max(board[1][j-1], board[1][j-2])
        board[1][j] += max(board[0][j-1], board[0][j-2])

    print(max(board[0][n-1], board[1][n-1]))