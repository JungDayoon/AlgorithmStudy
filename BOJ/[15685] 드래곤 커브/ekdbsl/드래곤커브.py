N = int(input())
dragonMap = [[False for _ in range(101)] for _ in range(101)]
pos = [[0, 1], [-1, 0], [0, -1], [1, 0]]

for _ in range(N):
    dx, dy, dir, generation = map(int, input().split())
    dragonList = []
    dragonDir = []
    # 초기화
    nx = dx + pos[dir][1]
    ny = dy + pos[dir][0]
    dragonList.append([dy, dx])
    dragonList.append([ny, nx])
    dragonMap[dy][dx] = True
    dragonMap[ny][nx] = True
    dragonDir.append(dir)

    for g in range(1, generation+1):
        nowLength = len(dragonDir)
        for dIdx in range(nowLength-1, -1, -1):
            nowDir = dragonDir[dIdx]
            end_y = dragonList[len(dragonList)-1][0]
            end_x = dragonList[len(dragonList)-1][1]

            newDir = (nowDir+1)%4
            ny = end_y + pos[newDir][0]
            nx = end_x + pos[newDir][1]
            dragonMap[ny][nx] = True
            dragonList.append([ny, nx])
            dragonDir.append(newDir)

squareCnt = 0
for i in range(100):
    for j in range(100):
        if dragonMap[i][j] and dragonMap[i+1][j] and dragonMap[i][j+1] and dragonMap[i+1][j+1]:
            squareCnt += 1

print(squareCnt)