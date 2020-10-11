N = int(input())
arr =[]
for i in range(N):
    arr.append(list(map(int, input().split())))
check = [[] * N for _ in range(N)]
for i in range(N):
    for j in range(N):
        check[i].append([0,0,0])

for i in range(1, N):
    if(arr[0][i] == 1):
        break
    check[0][i] = [0,0,1]

for i in range(1,N):
    for j in range(1, N):
        if(arr[i][j] == 1):
            continue
        check[i][j][0] = check[i-1][j][0]+check[i-1][j][1]
        if(arr[i-1][j-1] == 0 and arr[i-1][j] == 0 and arr[i][j-1] == 0):
            check[i][j][1] = (check[i - 1][j - 1][0] + check[i - 1][j - 1][1] + check[i - 1][j - 1][2])
        check[i][j][2] = (check[i][j-1][1] + check[i][j-1][2])
result = sum(check[N-1][N-1])
print(result)
