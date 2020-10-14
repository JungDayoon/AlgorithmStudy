def isIn(y, x):
    if 0 <= x < N - 1 and 0 <= y < H:
        return True
    return False


def checkPossibility():
    for j in range(N):
        nowLine = j
        for i in range(H):
            if nowLine > 0 and LadderInfo[i][nowLine - 1] == 1:
                nowLine -= 1
            elif nowLine < N - 1 and LadderInfo[i][nowLine] == 1:
                nowLine += 1
        if nowLine != j:
            return False

    return True


def makeLadder(now, goal, prev_y, prev_x):
    global flag

    if flag:
        return

    if now == goal:
        flag = checkPossibility()
        return

    for i in range(prev_y, H):
        prev_x = 0 if i > prev_y else prev_x
        for j in range(prev_x, N-1):
            if LadderInfo[i][j] == 0:
                if isIn(i, j+1) and LadderInfo[i][j+1] == 1:
                    continue
                if isIn(i, j-1) and LadderInfo[i][j-1] == 1:
                    continue
                # 설치가능
                LadderInfo[i][j] = 1
                makeLadder(now + 1, goal, i, j+2)
                if flag:
                    return
                LadderInfo[i][j] = 0


N, M, H = map(int, input().split())
LadderInfo = [[0 for _ in range(N-1)] for _ in range(H)]
# Ladder = []
for _ in range(M):
    a, b = map(int, input().split())
    a -= 1
    b -= 1
    # Ladder.append([a, b])
    LadderInfo[a][b] = 1

cnt = 0
flag = checkPossibility()
if not flag:
    while cnt < 3:
        cnt += 1

        makeLadder(0, cnt, 0, 0)
        if flag:
            break

print(-1 if not flag else cnt)
