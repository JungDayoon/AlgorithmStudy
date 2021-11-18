N,K = map(int,input().split())
DP = [[1]*(N+1) for _ in range(K)]

for i in range(1,K):
    for j in range(1,N+1):
        DP[i][j] = DP[i][j-1]+DP[i-1][j]

print(DP[K-1][N]%1000000000)