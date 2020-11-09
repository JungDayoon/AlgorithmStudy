import sys
from collections import deque

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def bfs(x, y, flag):
    q = deque()
    visit = [[0]*6 for _ in range(12)]
    q.append([x, y])
    cnt = 1
    visit[x][y] = 1
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < 12 and 0 <= ny < 6:
                if board[nx][ny] == board[x][y] and visit[nx][ny] == 0:
                    cnt += 1
                    visit[nx][ny] = 1
                    q.append([nx, ny])

    if cnt >= 4:
        flag += 1
        for i in range(12):
            for j in range(6):
                if visit[i][j] == 1:
                    board[i][j] = '.'

    return flag

def arraying():
    for i in range(10, -1, -1):
        for j in range(6):
            if board[i][j] != '.' and board[i+1][j] == '.':
                for k in range(i+1, 12):
                    if k == 11 and board[k][j] == '.':
                        board[k][j] = board[i][j]
                    elif board[k][j] != '.':
                        board[k-1][j] = board[i][j]
                        break
                board[i][j] = '.'

if __name__ == '__main__':
    board = [list(map(str, sys.stdin.readline().strip())) for _ in range(12)]
    answer = 0
    while True:
        cnt = 0
        for i in range(12):
            for j in range(6):
                if board[i][j] != '.':
                    cnt = bfs(i, j, cnt)
        if cnt == 0:
            print(answer)
            break
        else:
            answer += 1

        arraying()

