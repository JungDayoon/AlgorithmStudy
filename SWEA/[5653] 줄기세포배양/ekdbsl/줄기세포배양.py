T = int(input())
pos = [[1,0], [0,1], [-1,0], [0,-1]]

def isIn(y, x):
    if 0 <= x < M+2*K and 0 <= y < N+2*K:
        return True
    return False


for t_c in range(1, T+1):
    N, M, K = map(int, input().split())
    initMap = [[[int(x), 0, int(x)] for x in input().split()]for _ in range(N)]
    cellMap = [[[0,0,0] for _ in range(M+2*K)]for _ in range(N+2*K)]
    cellList = []
    for i in range(N):
        for j in range(M):
            if initMap[i][j][0] != 0:
                cellList.append([i+K, j+K, initMap[i][j][0]])
                initMap[i][j][1] = 2
            cellMap[i+K][j+K] = initMap[i][j]

    for time in range(1, K+1):
        cellList = sorted(cellList, key=lambda t:t[2], reverse=True)
        removeList = []
        nowLen = len(cellList)
        for idx in range(nowLen):
            cell = cellList[idx]

            cell_y = cell[0]
            cell_x = cell[1]
            cell_live = cell[2]

            if(cellMap[cell_y][cell_x][1] == 3):    # 활성화 상태라면
                for i in range(4):
                    ny = cell_y + pos[i][0]
                    nx = cell_x + pos[i][1]

                    if isIn(ny, nx) and cellMap[ny][nx][1] == 0:
                        cellMap[ny][nx][0] = cellMap[cell_y][cell_x][0]
                        cellMap[ny][nx][1] = 2
                        cellMap[ny][nx][2] = cellMap[cell_y][cell_x][0]
                        cellList.append([ny, nx, cellMap[ny][nx][0]])

            cellMap[cell_y][cell_x][2] -= 1
            if cellMap[cell_y][cell_x][2] == 0:
                if cellMap[cell_y][cell_x][1] == 2: # 활성화로 바꾸기
                    cellMap[cell_y][cell_x][2] = cellMap[cell_y][cell_x][0]
                    cellMap[cell_y][cell_x][1] = 3

                elif cellMap[cell_y][cell_x][1] == 3: # 죽은 세포로 바꾸기
                    cellMap[cell_y][cell_x][0] = 0
                    cellMap[cell_y][cell_x][1] = 1
                    removeList.append(cell)
        for rItem in removeList:
            cellList.pop(cellList.index(rItem))

        # print(cellList)

    print("#{} {}".format(t_c, len(cellList)))

