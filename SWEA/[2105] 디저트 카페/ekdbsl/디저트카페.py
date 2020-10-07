T = int(input())

pos = [[1,1], [1,-1], [-1,-1], [-1,1]]
dessertList = []

def isIn(y, x):
    if y>=0 and y<N and x >=0 and x<N:
        return True
    return False

# def copy(orig, new):
#     for i in range(N):
#         for j in range(N):
#             new[i][j] = orig[i][j]

def cafeTour(y, x, count_1, count_2, idx):
    global dessertList
    global maxDessert

    if idx == 2:
        for _ in range(count_1):
            ny = y + pos[idx][0]
            nx = x + pos[idx][1]

            if not isIn(ny, nx) or cafeMap[ny][nx] in dessertList:
                return

            dessertList.append(cafeMap[ny][nx])
            y = ny
            x = nx
            
        cafeTour(y, x, count_1, count_2, idx+1)
    if idx == 3:
        for _ in range(count_2):
            ny = y + pos[idx][0]
            nx = x + pos[idx][1]

            if ny == start_y and nx == start_x:
                dessertSum = len(dessertList)
                maxDessert = max(dessertSum, maxDessert)
                return
            
            if not isIn(ny, nx) or cafeMap[ny][nx] in dessertList:
                return

            dessertList.append(cafeMap[ny][nx])
            y = ny
            x = nx
        
        

    ny = y + pos[idx][0]
    nx = x + pos[idx][1]

    if isIn(ny, nx) and cafeMap[ny][nx] not in dessertList:

        dessertList.append(cafeMap[ny][nx])
        # tmp = [[False for _ in range(N)]for _ in range(N)]

        # copy(tmp, visited)
        tmpList = dessertList[:]

        if idx == 0:
            cafeTour(ny, nx, count_1+1, count_2, idx)
            dessertList = tmpList[:]
            # copy(visited, tmp)

            cafeTour(ny, nx, count_1, count_2+1, idx+1)
            dessertList = tmpList[:]
            # copy(visited, tmp)
        elif idx == 1:
            cafeTour(ny, nx, count_1, count_2+1, idx)
            dessertList = tmpList[:]
            # copy(visited, tmp)

            cafeTour(ny, nx, count_1, count_2, idx+1)
            dessertList = tmpList[:]

for test_case in range(1, T + 1):
    N = int(input())
    cafeMap = [[int(x) for x in input().split()]for y in range(N)]
    # start = [[False for _ in range(N)]for _ in range(N)]
    maxDessert = 0

    for i in range(N):
        for j in range(N):
            # if not start[i][j]:
                start_y = i
                start_x = j

                visited = [[False for _ in range(N)]for _ in range(N)]
                dessertList = []
                dessertList.append(cafeMap[i][j])
                cafeTour(i, j, 1, 0, 0)
    if maxDessert == 0:
        maxDessert = -1
    print("#{} {}".format(test_case, maxDessert))