import sys
input = sys.stdin.readline
T = int(input())
for t in range(T):
    N = int(input())
    dp = [list(map(int,input().split())) for _ in range(2)]
    for i in range(1,N):
        if i==1:
            dp[0][i]+=dp[1][i-1]
            dp[1][i]+=dp[0][i-1]
        else:
            dp[0][i]+=max(dp[1][i-1],dp[1][i-2])
            dp[1][i]+=max(dp[0][i-1],dp[0][i-2])

    print(max(map(max, dp)))
