from collections import deque

def isIn(y, x):
    if 0<= x <6 and 0<=y <12:
        return True
    return False


def bfs(y, x, type):
    global tmpList

    count = 0
    queue = deque()
    queue.append([y, x])
    visited[y][x] = True

    while queue:
        curr = queue.popleft()
        count += 1
        tmpList.append([curr[0], curr[1]])
        for i in range(4):
            ny = curr[0] + pos[i][0]
            nx = curr[1] + pos[i][1]

            if isIn(ny, nx) and not visited[ny][nx] and puyoMap[ny][nx] == type:
                queue.append([ny, nx])
                visited[ny][nx] = True

    return count

def removeEmpty():
    for j in range(6):
        list = []
        for i in range(12):
            if puyoMap[i][j] > 0:
                list.append(puyoMap[i][j])
                puyoMap[i][j] = 0
        last_idx = 11
        list.reverse()
        for l in list:
            puyoMap[last_idx][j] = l
            last_idx -= 1

puyoMap = [[0 for _ in range(6)]for _ in range(12)]
pos = [[-1,0], [1,0], [0,-1],[0,1]]
popCount = 0

for i in range(12):
    tmp = str(input())

    for j in range(6):
        if tmp[j] == 'R':
            puyoMap[i][j] = 1
        elif tmp[j] == 'G':
            puyoMap[i][j] = 2
        elif tmp[j] == 'B':
            puyoMap[i][j] = 3
        elif tmp[j] == 'P':
            puyoMap[i][j] = 4
        elif tmp[j] == 'Y':
            puyoMap[i][j] = 5

while True:
    removeList = []
    visited = [[False for _ in range(6)] for _ in range(12)]
    flag = False
    for i in range(12):
        for j in range(6):
            tmpList = []
            if not visited[i][j] and puyoMap[i][j] > 0:
                count = bfs(i, j, puyoMap[i][j])
                if count >= 4:
                    flag = True
                    removeList.append(tmpList)

    if not flag:
        break

    removeList = sum(removeList, [])

    for rItem in removeList:
        puyoMap[rItem[0]][rItem[1]] = 0

    removeEmpty()

    popCount += 1

print(popCount)