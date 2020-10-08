import sys
sys.stdin = open("input.txt", "r")
dx = [0, 0, -1, 1] #상하좌우
dy = [-1, 1, 0, 0] #상하좌우
max_height = 0
def dfs(road, x, y, K, visited, num, N):
    global max_height
    visited[y][x] = 1

    for i in range(4):
        nextx = x + dx[i]
        nexty = y + dy[i]

        if not(nextx == -1 or nexty == -1 or nextx == N or nexty == N):
            if(visited[nexty][nextx]!=1):
                if(road[nexty][nextx]>=road[y][x] and road[nexty][nextx]-road[y][x]<K):
                    temp = road[nexty][nextx]
                    road[nexty][nextx] = road[y][x]-1
                    dfs(road, nextx, nexty, 0, visited, num+1, N)
                    road[nexty][nextx] = temp
                elif(road[nexty][nextx]<road[y][x]):
                    dfs(road, nextx, nexty, K, visited, num+1, N)
    visited[y][x] = 0
    max_height = max(max_height, num)
    return
T = int(input())
for test_case in range(1, T + 1):
    max_height = 0
    N, K = map(int, input().split())
    road = []
    max_bong = 0
    for i in range(N):
        new_road = list(map(int, input().split()))
        road.append(new_road)
        max_bong = max(max_bong, max(new_road))

    for i in range(N):
        for j in range(N):
            if(road[i][j]==max_bong):
                visited = [[0]*N for _ in range(N)]
                dfs(road, j, i, K, visited, 1, N)

    print("#"+str(test_case)+" " +str(max_height))
