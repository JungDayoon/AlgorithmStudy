def isIn(y, x):
    if 0 <= x < N and 0 <= y < N:
        return True
    return False

N = int(input())
Map = [[int(x) for x in input().split()] for _ in range(N)]
pipeCnt = [[[0, 0, 0] for _ in range(N)] for _ in range(N)]
count = 0

pipeCnt[0][1][1] = 1
for j in range(2, N):
    for i in range(N):
        if Map[i][j] == 1:
            continue
        if isIn(i-1, j) and Map[i-1][j] == 0:
            pipeCnt[i][j][0] += pipeCnt[i-1][j][0] + pipeCnt[i-1][j][2]
        if isIn(i, j-1) and Map[i][j-1] == 0:
            pipeCnt[i][j][1] += pipeCnt[i][j-1][1] + pipeCnt[i][j-1][2]
        if isIn(i-1, j-1) and Map[i-1][j-1] == 0 and Map[i-1][j] == 0 and Map[i][j-1] == 0:
            pipeCnt[i][j][2] += pipeCnt[i-1][j-1][0] + pipeCnt[i-1][j-1][1] + pipeCnt[i-1][j-1][2]
            
print(sum(pipeCnt[N-1][N-1]))
