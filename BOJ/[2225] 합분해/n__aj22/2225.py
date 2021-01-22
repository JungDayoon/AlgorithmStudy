if __name__ == "__main__":
    N, K = map(int, input().split()) #20, 2

    dp = [[0]*(N+1)for i in range(K+1)]
    for i in range(N+1):
        dp[1][i]=1
    for i in range(K+1):
        dp[i][0] = 1
    for i in range(1, K+1):
        for j in range(1, N+1):
            dp[i][j] = dp[i][j-1]+dp[i-1][j]
    print(dp[K][N]%1000000000)

