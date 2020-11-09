N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]
check = [list([0,0,0] for _ in range(N)) for _ in range(N)]

check[0][1][0] = 1
for i in range(2, N):
    if not board[0][i]:
        check[0][i][0] = check[0][i-1][0]  # 첫번째줄은 벽이 없으면 오른쪽으로만 간다.
#  0: 오른쪽 1: 대각선 2: 아래쪽 이동
for i in range(1, N):
    for j in range(2, N):  # 시작에서 바로 대각선으로 내려와도 [1,2]
        if not board[i-1][j] and not board[i][j-1] and not board[i][j]:
            check[i][j][1] = sum(check[i-1][j-1])  # [1]: 대각선위의 0,1,2번째를 모두 본다.
        if not board[i][j]:
            check[i][j][0] = check[i][j-1][0]+check[i][j-1][1]  # [0]: 왼쪽 check의 0번째로 1번째를 본다.
            check[i][j][2] = check[i-1][j][1]+check[i-1][j][2]  # [2]: 위 check의 1번째 2번째를 본다.

print(sum(check[N-1][N-1]), end='')
