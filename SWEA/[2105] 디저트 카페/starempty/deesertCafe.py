dx = [1, 1, -1, -1]
dy = [1, -1, -1, 1]

def dfs(x, y, dir, cnt):
    global cx, cy, answer

    if dir == 3 and x == cx and y == cy:
        if answer < cnt:
            answer = cnt
    elif x < 0 or x  >= n or y < 0 or y >= n:
        return
    elif board[x][y] in d:
        return
    else:
        d.append(board[x][y])
        if dir == 0 or dir == 1:
            dfs(x+dx[dir], y+dy[dir], dir, cnt+1)
            dfs(x+dx[dir+1], y+dy[dir+1], dir+1, cnt+1)
        elif dir == 2:
            if x+y != cx+cy:
                dfs(x+dx[2], y+dy[2], dir, cnt+1)
            else:
                dfs(x+dx[3], y+dy[3], dir+1, cnt+1)
        else:
            dfs(x+dx[3], y+dy[3], dir, cnt+1)
        d.remove(board[x][y])


if __name__ == '__main__':
    T = int(input())
    for tc in range(1, T+1):
        n = int(input())
        board = [list(map(int, input().split())) for _ in range(n)]
        visit = [[False]*n for _ in range(n)]
        answer = -1
        d = []

        for i in range(n):
            for j in range(1, n-1):
                cx, cy = i, j
                d.append(board[i][j])
                dfs(i+1, j+1, 0, 1)  # append 하고 오른쪽 아래부터 진행, append 하고 시작하기때문에 cnt = 1
                d.remove(board[i][j])

        print("#{} {}".format(tc, answer))