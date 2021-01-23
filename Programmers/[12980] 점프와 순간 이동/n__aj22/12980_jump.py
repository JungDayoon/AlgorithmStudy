def solution(n):
    dp = [0, 1]

    for i in range(2, N+1):
        dp.append(dp[i-1]+1)
        if(i%2==0):#짝수면
            dp[i] = min(dp[i], dp[i//2])

    return dp[N]

if __name__ == "__main__":
    N = int(input())
    print(solution(N))