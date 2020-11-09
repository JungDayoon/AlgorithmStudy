N = int(input())
price = [int(x) for x in input().split()]
price.insert(0, 0)
dp = [0 for _ in range(N+1)]
dp[1] = price[1]

for i in range(2, N+1):
    for j in range(0, i+1):
        dp[i] = max(dp[i], dp[j] + price[i-j])

print(dp[N])

