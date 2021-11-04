import sys
input = sys.stdin.readline
N = int(input())
house = [ list(map(int,input().split())) for _ in range(N)]
pipe = [[0,0,0,1],1]
answer = [ [[0,0,0] for _ in range(N)] for _ in range(N)]

for i in range(1,N):
    if house[0][i]==1:
        break
    answer[0][i][0] = 1

for i in range(1,N):
    for j in range(1,N):
        if house[i][j]==1:
            continue
        # → = → + ↘ [i,j-1]
        answer[i][j][0] = answer[i][j-1][0]+answer[i][j-1][2]
        # ↓ = ↘ + ↓ [i-1][j]
        answer[i][j][1] = answer[i-1][j][2]+answer[i-1][j][1]
        # ↘ = ↘ + ↓ + → [i-1][j-1]
        if house[i-1][j]==0 and house[i][j-1]==0:
            answer[i][j][2] = sum(answer[i-1][j-1])

print(sum(answer[N-1][N-1]))