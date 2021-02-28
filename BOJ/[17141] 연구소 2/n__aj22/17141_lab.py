import collections
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
min_time = float('inf')
def isin(y, x):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def spread_virus(virus_list):
    visited = [[-1]*N for _ in range(N)]
    deque = collections.deque()
    now_max = 0
    for virus in virus_list:
        deque.append([virus[0], virus[1], 0])
        visited[virus[0]][virus[1]] = 0
    
    while(deque):
        nowy, nowx, time = deque.popleft()
        for i in range(4):
            nexty = dy[i]+nowy
            nextx = dx[i]+nowx
            if(isin(nexty, nextx)):
                if(visited[nexty][nextx] == -1 and arr[nexty][nextx] != 1):
                    visited[nexty][nextx] = time+1
                    now_max = max(now_max, time+1)
                    deque.append([nexty, nextx, time+1])
    flag = True
    for i in range(N):
        for j in range(N):
            if(visited[i][j] == -1 and arr[i][j] == 0):
                flag = False
                break
        if(flag == False):
            break
    
    if(flag == True):
        global min_time
        min_time = min(now_max, min_time)

    return


def choose_virus(count, virus_list, y, x):
    if(count == M):
        # 바이러스 퍼트리기 
        spread_virus(virus_list)
        return
    
    for i in range(N):
        if(i<y):
            continue
        for j in range(N):
            if(i==y and j<x):
                continue
            if(arr[i][j] == 2):
                if([i, j] not in virus_list):
                    virus_list.append([i, j])
                    choose_virus(count+1, virus_list, i, j+1)
                    virus_list.pop(-1)    

if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    for i in range(N):
        arr.append(list(map(int, input().split())))
    choose_virus(0, [], 0, 0)

    if(min_time == float('inf')):
        print(-1)
    else:
        print(min_time)