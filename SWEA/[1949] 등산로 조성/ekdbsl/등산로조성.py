def isIn(y, x):
    if 0 <= y < N and 0 <= x < N:
        return True
    return False


def findRoute(y, x, cnt, K):
    global maxCnt

    flag = False

    for i in range(4):
        ny = y + pos[i][0]
        nx = x + pos[i][1]

        if isIn(ny, nx) and not visited[ny][nx]:
            if hikingMap[ny][nx] < hikingMap[y][x]:
                visited[ny][nx] = True
                findRoute(ny, nx, cnt + 1, K)
                flag = True
                visited[ny][nx] = False
            elif K > 0 and hikingMap[ny][nx] - K < hikingMap[y][x]:
                visited[ny][nx] = True
                temp = hikingMap[ny][nx]
                hikingMap[ny][nx] = hikingMap[y][x] - 1
                findRoute(ny, nx, cnt + 1, 0)
                hikingMap[ny][nx] = temp
                flag = True
                visited[ny][nx] = False

    if not flag:  # 더 이상 이동 불가
        maxCnt = max(cnt, maxCnt)


def findHighest(Map):
    maxHeight = 0
    List = []
    for y in range(N):
        rowMax = max(Map[y])
        maxHeight = max(rowMax, maxHeight)

    for y in range(N):
        for x in range(N):
            if Map[y][x] == maxHeight:
                List.append([y, x])
    return List


T = int(input())
pos = [[-1, 0], [1, 0], [0, -1], [0, 1]]

for t_c in range(1, T + 1):
    N, K = map(int, input().split())
    hikingMap = [[int(x) for x in input().split()] for _ in range(N)]
    maxCnt = 0

    startList = findHighest(hikingMap)

    for s in startList:
        visited = [[False for _ in range(N)] for _ in range(N)]
        visited[s[0]][s[1]] = True
        findRoute(s[0], s[1], 1, K)

    print("#{} {}".format(t_c, maxCnt))

