import sys
input = sys.stdin.readline
N = int(input())
nlis = list(map(int,input().split()))
dp = [1 for _ in range(N)]

for j in range(N):
    for i in range(0,j):
        if nlis[i]>nlis[j]:
            if dp[i]+1>dp[j]:
                dp[j]=dp[i]+1

dp.sort()
print(dp.pop())


