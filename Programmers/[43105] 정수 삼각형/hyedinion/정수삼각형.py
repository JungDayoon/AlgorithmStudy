def solution(triangle):
    answer = 0
    for i in range(1,len(triangle)):
        for j in range(len(triangle[i])):
            temp1 = triangle[i][j]
            temp2 = triangle[i][j]
            if j!=0:
                temp1 +=triangle[i-1][j-1]
            if j!=len(triangle[i])-1:
                temp2 += triangle[i-1][j]
            triangle[i][j] = max(temp1,temp2)
    return max(triangle[-1])

print(solution([[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]))