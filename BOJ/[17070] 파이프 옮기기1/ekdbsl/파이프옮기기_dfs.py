def isEnd(y, x):
    if y == N-1 and x == N-1:
        return True
    return False

def isIn(y, x):
    if 0 <= x < N and 0 <= y <N:
        return True
    return False

def check(y, x, dir):
    if dir == 0:
        if Map[y+1][x] == 0:
            return True
        return False

    elif dir == 1:
        if Map[y][x+1] == 0:
            return True
        return False

    elif dir == 2:
        if Map[y+1][x] == 0 and Map[y][x+1] == 0 and Map[y+1][x+1] == 0:
            return True
        return False

def dfs(y, x, prev_dir):
    global count

    for dir in dirInfo[prev_dir]:
        ny = y + pos[dir][0]
        nx = x + pos[dir][1]

        if isEnd(ny, nx):
            count += 1
            return
        if isIn(ny, nx) and check(y, x, dir):
            dfs(ny, nx, dir)

N = int(input())
Map = [[int(x) for x in input().split()]for _ in range(N)]
pos = [[1,0],[0,1],[1,1]]
dirInfo = [[0,2],[1,2],[0,1,2]]
count = 0

dfs(0, 1, 1)
print(count)