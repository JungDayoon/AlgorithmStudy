import sys
sys.stdin = open("/Users/najihye/algo2/SWEA/[1953] 탈주범 검거/n__aj22/input.txt", "r")
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
T = int(input())
visited_num = 0
timecheck = [[0]*10 for _ in range(10)]
def check(num):
    if(num == 0):
        return 2
    elif(num==1):
        return 3
    elif(num ==2):
        return 0
    elif(num==3):
        return 1
def underground(option):
    if(option == 1):
        return [0,1,2,3]
    if(option == 2):
        return [0,2]
    if(option == 3):
        return [1,3]
    if(option == 4):
        return [0,1]
    if(option == 5):
        return [1,2]
    if(option == 6):
        return [2,3]
    if(option == 7):
        return [3,0]
def bfs(road, visited, nowx, nowy, time, N, M):
    queue = [[nowy, nowx, time]]
    global visited_num
    while queue:
        vertex = queue.pop(0)
        y = vertex[0]
        x = vertex[1]
        t = vertex[2]
        visited[y][x] = 1
        if t == 1:
            continue
        move_list = list(underground(road[y][x]))
        for i in move_list:
            nextx = x+dx[i]
            nexty = y+dy[i]
            if (nextx == -1 or nexty == -1 or nextx == M or nexty == N):
                continue
            if(visited[nexty][nextx] ==1):
                continue
            if(road[nexty][nextx] == 0):
                continue
            if (check(i) in list(underground(road[nexty][nextx]))):
                queue.append([nexty, nextx, t-1])
                #dfs(road, visited, nextx, nexty, time-1, N, M)
   
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    #세로 크기 N, 가로 크기 M, 맨홀 뚜껑이 위치한장소의 세로 위치 R, 가로 위치 C, 그리고 탈출 후 소요된 시간 L
    N, M, R, C, L = map(int, input().split())
    road = []
    for i in range(N):
        road.append(list(map(int, input().split())))
    visited = [[0]*M for _ in range(N)] #방문 정보를 저장해주기 위함
    visited_num = 0
    bfs(road, visited, C, R, L, N, M)
    for i in range(N):
        for j in range(M):
            if(visited[i][j] == 1):visited_num+=1
    print("#"+str(test_case)+" "+str(visited_num))



