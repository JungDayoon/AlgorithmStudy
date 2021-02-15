total_num = 0
dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
def backtracking(nowx, nowy, now_percentage, choose_num):
    if(choose_num == n):
        global total_num
        total_num+=now_percentage
        return
    for i in range(4):
        nextx = nowx + dx[i]
        nexty = nowy + dy[i]
        if(visited[nexty][nextx] == False):
            visited[nexty][nextx] = True
            backtracking(nextx, nexty, now_percentage*percentage[i], choose_num+1)
            visited[nexty][nextx] = False
    return
if __name__ == "__main__":
    n, E, W, N, S = map(int, input().split())
    visited = [[False]*(n*2+1) for _ in range(n*2+1)]
    percentage = [E/100, W/100, N/100, S/100]
    visited[n][n] = True
    backtracking(n, n, 1, 0)
    print(total_num)

