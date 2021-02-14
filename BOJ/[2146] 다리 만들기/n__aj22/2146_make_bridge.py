visited = []
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
min_bridge = 9999
def isin(x, y):
    if(0<=x<N and 0<=y<N):
        return True
    return False
def check_island(cnt, y, x): #섬에 번호 붙여준다. 
    visited[y][x] = True
    map_arr[y][x] = cnt
    queue = []
    queue.append([y,x])
    while(queue):
        y,x = queue.pop()
        for i in range(4):
            nexty = y + dy[i]
            nextx = x + dx[i]
            if(isin(nextx, nexty)):
                if(visited[nexty][nextx] == False and map_arr[nexty][nextx] != 0): #방문한 적이 없고, map_arr 의 값이 0이 아니면, 
                    visited[nexty][nextx] = True
                    map_arr[nexty][nextx] = cnt
                    queue.append([nexty, nextx])
    return
def make_bridge(index):
    # bridge = [[0]*N for _ in range(N)]
    bridge_visited = [[False]*N for _ in range(N)]
    queue = []
    find_flag = False
    for i in range(N):
        for j in range(N):
            if(map_arr[i][j] == index):
                bridge_visited[i][j] = True
                queue.append([i, j, 0]) #y, x, cnt 
    while(queue):
        y, x, cnt = queue.pop(0)
        for i in range(4):
            nexty = y + dy[i]
            nextx = x + dx[i]
            if(isin(nextx, nexty)):
                if(bridge_visited[nexty][nextx] == False):
                    if(map_arr[nexty][nextx] != index and map_arr[nexty][nextx] != 0):
                        find_flag = True
                        global min_bridge
                        min_bridge = min(min_bridge, cnt)
                        break
                    # bridge[nexty][nextx] = cnt+1
                    bridge_visited[nexty][nextx] = True
                    queue.append([nexty, nextx, cnt+1])
        if(find_flag == True):
            break
    return

if __name__ == "__main__":
    N = int(input())
    map_arr = []
    visited = [[False]*N for _ in range(N)]
    for i in range(N):
        map_arr.append(list(map(int, input().split())))
    count = 0
    for i in range(N):
        for j in range(N):
            if(visited[i][j] == False and map_arr[i][j] > 0):#방문한 적이 없다면 
                count += 1
                check_island(count, i, j)

    # for i in map_arr:
    #     print(i)
    for i in range(count):
        make_bridge(i+1)
    print(min_bridge)