T = int(input())

pos = [[1,1], [1,-1], [-1,-1], [-1,1]] # ↘ ↙ ↖ ↗ 방향으로 움직임
dessertList = []

def isIn(y, x):
    if y>=0 and y<N and x >=0 and x<N:
        return True
    return False

def cafeTour(y, x, count_1, count_2, idx):
    global dessertList
    global maxDessert

    if idx == 2:
        for _ in range(count_1):
            y += pos[idx][0]
            x += pos[idx][1]

            if not isIn(y, x) or cafeMap[y][x] in dessertList:
                return

            dessertList.append(cafeMap[y][x]) 
            
        cafeTour(y, x, count_1, count_2, idx+1) # ↗ 방향으로 전환
    if idx == 3:
        for _ in range(count_2):
            y += pos[idx][0]
            x += pos[idx][1]

            if y == start_y and x == start_x: # 원래 있던 자리로 돌아옴 -> 즉 사각형 모양으로 움직임이 끝남
                dessertSum = len(dessertList)
                maxDessert = max(dessertSum, maxDessert)
                return
            
            if not isIn(y, x) or cafeMap[y][x] in dessertList:
                return

            dessertList.append(cafeMap[y][x])
        
    ny = y + pos[idx][0]
    nx = x + pos[idx][1]

    if isIn(ny, nx) and cafeMap[ny][nx] not in dessertList:

        dessertList.append(cafeMap[ny][nx])
        tmpList = dessertList[:]

        if idx == 0:
            cafeTour(ny, nx, count_1+1, count_2, idx) # ↘ 방향으로 더 움직임
            dessertList = tmpList[:]

            cafeTour(ny, nx, count_1, count_2+1, idx+1) # ↙ 방향으로 전환
            dessertList = tmpList[:]
        elif idx == 1:
            cafeTour(ny, nx, count_1, count_2+1, idx) # ↙ 방향으로 더 움직임
            dessertList = tmpList[:]

            cafeTour(ny, nx, count_1, count_2, idx+1) # ↖ 방향으로 전환
            dessertList = tmpList[:]

for test_case in range(1, T + 1):
    N = int(input())
    cafeMap = [[int(x) for x in input().split()]for y in range(N)]
    maxDessert = 0

    for i in range(N):
        for j in range(N):
            start_y = i
            start_x = j
            dessertList = []
            dessertList.append(cafeMap[i][j])
            cafeTour(i, j, 1, 0, 0)

    if maxDessert == 0:
        maxDessert = -1

    print("#{} {}".format(test_case, maxDessert))