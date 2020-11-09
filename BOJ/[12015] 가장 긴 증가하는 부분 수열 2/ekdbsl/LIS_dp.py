N = int(input())
number = [int(x) for x in input().split()]
dp = [1] * N
for i in range(N):
    for j in range(i):
        if number[j] < number[i]:
            dp[i] = max(dp[i], dp[j]+1)

print(max(dp))