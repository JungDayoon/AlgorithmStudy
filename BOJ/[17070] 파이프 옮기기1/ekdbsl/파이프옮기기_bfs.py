def isEnd(y, x):
    if y == N-1 and x == N-1:
        return True
    return False

def isIn(y, x):
    if 0 <= x < N and 0 <= y < N:
        return True
    return False

def check(y, x, dir):
    if dir == 0:
        if Map[y+1][x] == 1:
            return False
    elif dir == 1:
        if Map[y][x+1] == 1:
            return False
    elif dir == 2:
        if Map[y+1][x] == 1 or Map[y][x+1] == 1 or Map[y+1][x+1] == 1:
            return False
    return True

def bfs(y, x, dir):
    global count
    queue = []
    queue.append([y, x, dir])

    while queue:
        curr = queue.pop(0)
        for dir in dirInfo[curr[2]]:
            ny = curr[0] + pos[dir][0]
            nx = curr[1] + pos[dir][1]

            if isEnd(ny, nx):
                count += 1
            elif isIn(ny, nx) and check(curr[0], curr[1], dir):
                queue.append([ny, nx, dir])

N = int(input())
Map = [[int(x) for x in input().split()]for _ in range(N)]
pos = [[1,0],[0,1],[1,1]]
dirInfo = [[0,2],[1,2],[0,1,2]]
count = 0

bfs(0, 1, 1)
print(count)