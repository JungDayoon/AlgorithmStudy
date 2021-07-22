def calculate(num1, num2, op):
    if op == '+':
        return num1+num2
    elif op == '-':
        return num1-num2
    else:
        return num1*num2
if __name__ == "__main__":
    N = int(input())
    arr = list(input())
    maxArr = [[float('-inf')]*N for _ in range(N)]
    minArr = [[float('inf')]*N for _ in range(N)]

    for i in range(0, N, 2):
        maxArr[i][i], minArr[i][i] = int(arr[i]), int(arr[i])
    
    for k in range(2, N, 2): #간격
        for i in range(0, N-k, 2):#시작점
            for j in range(i+1, i+k, 2): 
                op = arr[j]
                result = [0]*4
                ##max max
                result[0] = calculate(maxArr[i][j-1], maxArr[j+1][i+k], op)
                ##min max
                result[1] = calculate(minArr[i][j-1], maxArr[j+1][i+k], op)
                ##max min
                result[2] = calculate(maxArr[i][j-1], minArr[j+1][i+k], op)
                ##min min
                result[3] = calculate(minArr[i][j-1], minArr[j+1][i+k], op)

                result.sort()

                maxArr[i][i+k] = max(maxArr[i][i+k], result[3])
                minArr[i][i+k] = min(minArr[i][i+k], result[0])


    print(maxArr[0][N-1])