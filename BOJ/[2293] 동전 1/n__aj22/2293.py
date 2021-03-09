if __name__ == "__main__":
    N, K = map(int, input().split())
    coin = []
    for i in range(N):
        coin.append(int(input()))
    coin.sort()

    dp = [0]*(K+1)
    dp[0] = 1
    for c in coin:
        for j in range(c, K+1):
            dp[j]+=dp[j-c]
    print(dp[K])

