def dfs(x, y, cnt, k, n):
    global answer
    if answer < cnt+1:
        answer = cnt+1
    visit[x][y] = 1
    dy = [0, 1, 0, -1]
    dx = [1, 0, -1, 0]
    for i in range(4):  # 상하좌우 움직이기
        ny = y + dy[i]
        nx = x + dx[i]
        if 0 <= ny < n and 0 <= nx < n:
            if visit[nx][ny] == 0:
                if info[nx][ny] < info[x][y]:
                    dfs(nx, ny, cnt+1, k, n)
                elif info[nx][ny]-k < info[x][y]:  # 깎고 조성
                    tmp = info[nx][ny]
                    info[nx][ny] = info[x][y] - 1
                    dfs(nx, ny, cnt+1, 0, n)
                    info[nx][ny] = tmp
    visit[x][y] = 0


T = int(input())

for tc in range(1, T+1):
    n, k = map(int, input().split())
    info = [list(map(int, input().split())) for _ in range(n)]
    visit = [[0]*n for _ in range(n)]

    answer = 0
    maxValue = 0
    for i in info:
        if maxValue < max(i):
            maxValue = max(i)  # 가장 높은 봉우리 찾기
    check = []
    for i in range(n):
        for j in range(n):
            if info[i][j] == maxValue:  # 높은 봉우리 좌표값 찾기
                check.append((i, j))

    for i in range(len(check)):
        dfs(check[i][0], check[i][1], 0, k, n)

    print("#{} {}".format(tc, answer))