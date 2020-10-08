from collections import deque

Tunnel = [[], [0, 1, 2, 3], [0, 1], [2, 3], [0, 3], [1, 3], [1, 2], [0, 2]]
inOut = [1, 0, 3, 2]
pos = [[-1, 0], [1, 0], [0, -1], [0, 1]]

T = int(input())


def isIn(y, x):
    if 0 <= y < N and 0 <= x < M:
        return True
    return False


def bfs(hole_y, hole_x):
    queue = deque()
    queue.append([hole_y, hole_x])
    visited[hole_y][hole_x] = True
    time = 1
    count = 1
    while queue:
        if time == L:
            return count
        time += 1
        for i in range(len(queue)):
            curr = queue.popleft()
            cy = curr[0]
            cx = curr[1]
            TunnelNum = Map[cy][cx]
            for tDir in Tunnel[TunnelNum]:
                ny = cy + pos[tDir][0]
                nx = cx + pos[tDir][1]

                if isIn(ny, nx) and not visited[ny][nx] and inOut[tDir] in Tunnel[Map[ny][nx]]:
                    queue.append([ny, nx])
                    visited[ny][nx] = True
                    count += 1

    return count


for t_c in range(1, T + 1):
    N, M, R, C, L = map(int, input().split())
    Map = [[int(x) for x in input().split()] for _ in range(N)]
    visited = [[False for _ in range(M)] for _ in range(N)]

    print("#{} {}".format(t_c, bfs(R, C)))
