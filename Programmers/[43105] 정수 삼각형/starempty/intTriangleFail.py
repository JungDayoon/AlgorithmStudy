def solution(triangle):
    answer = 0
    dp = [triangle[0]]
    for i in range(len(triangle)-1):
        tmp = []
        for j in range(len(triangle[i])):
            cur = dp[i][j]
            for k in range(j, j+2):
                tmp.append(cur+triangle[i+1][k])
        dp.append(tmp)
    answer = (max(dp[len(dp)-1]))
    print(answer)
    return answer
    
solution([[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]])