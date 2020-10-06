def findClosest(col):
    for i in range(1, R+1):
        if len(fishMap[i][col]) > 0:
            return i
    return 0

def moveFish(y, x):
    Speed = fishMap[y][x][0][0]
    Dir = fishMap[y][x][0][1]
    Size = fishMap[y][x][0][2]

    fishMap[y][x].pop(0)

    newSpeed = Speed
    change_y = y
    change_x = x
    while True:
        if Dir == 1:
            if change_y - start_r < newSpeed:
                newSpeed -= (change_y-start_r)
                Dir = 2
                change_y = start_r
            else:
                change_y -= newSpeed
                fishMap[change_y][x].append([Speed, Dir, Size])
                break
        elif Dir == 2:
            if end_r - change_y < newSpeed:
                newSpeed -= (end_r - change_y)
                Dir = 1
                change_y = end_r
            else:
                change_y += newSpeed
                fishMap[change_y][x].append([Speed, Dir, Size])
                break
        elif Dir == 3:
            if end_c - change_x < newSpeed:
                newSpeed -= (end_c - change_x)
                Dir = 4
                change_x = end_c
            else:
                change_x += newSpeed
                fishMap[y][change_x].append([Speed, Dir, Size])
                break
        elif Dir == 4:
            if change_x - start_c < newSpeed:
                newSpeed -= (change_x - start_c)
                Dir = 3
                change_x = start_c
            else:
                change_x -= newSpeed
                fishMap[y][change_x].append([Speed, Dir, Size])
                break

R, C, M = map(int, input().split())
fishMap = [[[]for _ in range(C+1)] for _ in range(R+1)]
fishMan = 0
fishCnt = 0
start_r = 1
end_r = R
start_c = 1
end_c = C

for m in range(M):
    r, c, s, d, z = map(int, input().split())
    fishMap[r][c].append([s, d, z])

while fishMan < C:
    fishMan += 1
    
    row = findClosest(fishMan)
    if row > 0:
        #가장 가까운 상어를 없앰
        getFish = fishMap[row][fishMan][0]
        fishMap[row][fishMan].pop(0)
        fishCnt += getFish[2]

    moveList = []
    for i in range(1, R+1):
        for j in range(1, C+1):
            if len(fishMap[i][j]) > 0:
                moveList.append([i, j])
    
    for ml in moveList:
        moveFish(ml[0], ml[1])
    
    for i in range(1, R+1):
        for j in range(1, C+1):
            if len(fishMap[i][j])> 1: # 한 좌표에 여러 마리의 상어
                fishMap[i][j] = sorted(fishMap[i][j], key=lambda t: t[2], reverse=True)
                winner = fishMap[i][j][0]
                fishMap[i][j].clear()
                fishMap[i][j].append(winner)

print(fishCnt)