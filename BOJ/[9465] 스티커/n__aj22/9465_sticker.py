def choose(c, status, N):
    if(c == N):
        return 0
    if(dp[status][c] != -1):
        return dp[status][c]

    result = choose(c+1, 0,N)
    if(status != 1):
        result = max(result, choose(c+1, 1, N)+sticker_arr[0][c])
    if(status != 2):
        result = max(result, choose(c+1, 2, N)+sticker_arr[1][c])
    dp[status][c] = result
    return result

if __name__ == "__main__":
    T = int(input())
    for t in range(T):
        n = int(input())
        sticker_arr = []
        for i in range(2):
            sticker_arr.append(list(map(int, input().split())))
        dp = [[0]*(n+1) for i in range(3)]
        
        for i in range(1, n+1):
            dp[0][i] = max(dp[0][i-1], dp[1][i-1], dp[2][i-1])
            dp[1][i] = max(dp[0][i-1]+sticker_arr[1][i-1], dp[2][i-1]+sticker_arr[1][i-1])
            dp[2][i] = max(dp[0][i-1]+sticker_arr[0][i-1], dp[1][i-1]+sticker_arr[0][i-1])

        print(max(dp[0][n], dp[1][n], dp[2][n]))
