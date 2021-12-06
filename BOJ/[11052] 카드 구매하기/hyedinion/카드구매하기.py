N = int(input())
P = list(map(int,input().split()))
W = [ [P[i]/(i+1),i+1] for i in range(N)]
W.sort()
DP = [ [0 for _ in range(N+1)] for _ in range(N+1)] 

for I in range(1,N+1):
    inum = W[I-1][1]
    for J in range(1,N+1):
        if J-inum>=0:
            DP[I][J] = max(P[inum-1]+DP[I][J-inum],DP[I-1][J])
        else:
            DP[I][J]=DP[I-1][J]

print(DP[N][N])
