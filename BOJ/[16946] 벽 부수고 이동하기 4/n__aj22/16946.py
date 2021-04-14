import collections
dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]
def isin(y, x):
    if(0<=y<N and 0<=x<M):
        return True
    return False
def bfs(y, x, visited, index):
    check = []
    queue = collections.deque()
    visited[y][x] = [0,0]
    queue.append([y, x])
    while(queue):
        y, x = queue.popleft()
        check.append([y, x])
        for i in range(4):
            nexty, nextx = y+dy[i], x + dx[i]
            if isin(nexty, nextx) and visited[nexty][nextx][0] == -1 and arr[nexty][nextx] == '0':
                visited[nexty][nextx][0] = 0
                queue.append([nexty, nextx])
    for point in check:
        y, x = point
        visited[y][x] = [len(check), index]
    return 

if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    for i in range(N):
        arr.append(list(input()))
    visited = [[-1]*M for _ in range(N)]
    for i in range(N):
        for j in range(M):
            visited[i][j] = [-1, -1]
    cnt = 0
    for i in range(N):
        for j in range(M):
            if(arr[i][j] == '0' and visited[i][j][0] == -1):
                bfs(i, j, visited, cnt)
                cnt+=1

    for i in range(N):
        for j in range(M):
            if(visited[i][j][0] == -1):
                answer = 1
                check = []
                for k in range(4):
                    nexty, nextx = i+dy[k], j+dx[k]
                    if(isin(nexty, nextx) and visited[nexty][nextx][0]!=-1 and visited[nexty][nextx][1] not in check):
                        answer+=visited[nexty][nextx][0]
                        check.append(visited[nexty][nextx][1])
                arr[i][j] = str(answer%10)
            else:
                arr[i][j] = '0'

    for i in arr:
        print(''.join(i))             