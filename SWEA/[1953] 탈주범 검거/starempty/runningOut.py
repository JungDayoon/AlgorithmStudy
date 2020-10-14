from collections import deque


tunnel = {
    0: (),
    1: ((1, 0), (0, 1), (-1, 0), (0, -1)),
    2: ((1, 0), (-1, 0)),
    3: ((0, 1), (0, -1)),
    4: ((-1, 0), (0, 1)),
    5: ((1, 0), (0, 1)),
    6: ((1, 0), (0, -1)),
    7: ((-1, 0), (0, -1))
}


T = int(input())
for tc in range(1, T+1):
    answer = 1
    n, m, r, c, L = map(int, input().split())
    info = [list(map(int, input().split())) for _ in range(n)]
    q = deque([(r, c)])
    visit = [[0]*m for _ in range(n)]
    visit[r][c] = 1
    while q:
        x, y = q.popleft()  # 현재 위치에서
        for dx, dy in tunnel[info[x][y]]:
            nx, ny = x+dx, y+dy  # 움직였을 때 최종값
            if not 0 <= nx < n or not 0 <= ny < m:  # 갈 수 없는 범위
                continue
            if (-dx, -dy) in tunnel[info[nx][ny]]:  # 연결
                if not visit[nx][ny] and info[nx][ny]:  # 연결된 이곳에 방문한 적이 없고, 터널이 있다면(0이 아니라면)
                    visit[nx][ny] = visit[x][y] + 1
                    q += [(nx, ny)]
                    if visit[nx][ny] <= L:  # while 문에서는 모든 경우의 수를 탐색(BFS)
                        answer += 1

    print(f"#{tc} {answer}")
