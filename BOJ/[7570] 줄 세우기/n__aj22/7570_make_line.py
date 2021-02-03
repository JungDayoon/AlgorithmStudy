if __name__ == "__main__":
    n = int(input())
    arr = list(map(int, input().split()))
    dp = [0]*(n+1)
    for num in arr:
        dp[num] = dp[num-1]+1
    
    print(n - max(dp))
