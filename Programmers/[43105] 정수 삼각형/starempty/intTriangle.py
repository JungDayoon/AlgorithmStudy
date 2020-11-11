def solution(triangle):
    answer = 0
    dp = [triangle[0]]
    for i, cur in enumerate(triangle):
        if i == 0:
            continue
        tmp = []
        length = len(cur)-1
        for j, value in enumerate(cur):
            if j == 0:
                tmp.append(dp[i-1][0]+value)
            elif j == length:
                tmp.append(dp[i-1][length-1]+value)
            else:
                tmp.append(max(dp[i - 1][j - 1], dp[i - 1][j]) + value)
        dp.append(tmp)
    #print(dp[-1])
    answer = max(dp[-1])
    return answer
    
solution([[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]])