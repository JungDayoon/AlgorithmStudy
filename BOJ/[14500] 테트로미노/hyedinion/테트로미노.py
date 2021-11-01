import sys
input = sys.stdin.readline
N,M = map(int,input().split())
paper = [list(map(int,input().split())) for _ in range(N)]
D = [[-1,0],[1,0],[0,-1],[0,1]]

answer = 0
def find_tetromino(n,m,paper,depth,total,check):
    global answer
    if depth==4:
        answer = max(answer,total)
        return

    for d in D:
        newI = n+d[0]
        newJ = m+d[1]
        if 0<=newI<N and 0<=newJ<M and not check[newI][newJ]:
            if depth==2:
                check[newI][newJ]=True
                find_tetromino(n,m,paper,depth+1,total+paper[newI][newJ],check)
                check[newI][newJ]=False
            check[newI][newJ]=True
            find_tetromino(newI,newJ,paper,depth+1,total+paper[newI][newJ],check)
            check[newI][newJ]=False
    return

check = [[False]*M for _ in range(N)]
for n in range(N):
    for m in range(M):
        check[n][m]=True
        find_tetromino(n,m,paper,1,paper[n][m],check)
        check[n][m]=False

print(answer)