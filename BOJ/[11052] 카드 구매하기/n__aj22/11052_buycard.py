def find_maxmoney(arr, N):
    dp = [0 for i in range(N+1)]

    for i in range(1, N+1):
        for j in range(i, N+1):
            dp[j] = max(dp[j], dp[j-i]+arr[i])
    
    return dp[N]

if __name__ == "__main__":
    N = int(input())
    arr = list(map(int, input().split()))
    arr.insert(0, 0)

    print(find_maxmoney(arr, N))

