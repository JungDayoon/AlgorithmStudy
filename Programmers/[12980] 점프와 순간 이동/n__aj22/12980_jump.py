def solution(n):
    dp = [0, 1]

    for i in range(2, N+1):
        dp.append(dp[i-1]+1)
        if(i%2==0):#짝수면
            dp[i] = min(dp[i], dp[i//2])

    return dp[N]

def recursive(n):
    cnt = 0
    while(n>0):
        if(n%2 == 0):
            n = n//2
        else:
            n = n-1
            cnt+=1
    return cnt

if __name__ == "__main__":
    N = int(input())
    arr = [-1 for i in range(N+1)]
    arr[0] = 1
    arr[1] = 1
    print(recursive(N))