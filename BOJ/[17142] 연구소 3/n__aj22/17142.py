answer = -1
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
def isin(y, x):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def spread_virus(choose_list):
    queue = []
    visited = [[-1]*N for _ in range(N)]
    time = 0
    for i in choose_list:
        y, x = virus[i]
        queue.append(virus[i])
        visited[y][x] = 0
    while(queue):
        y, x = queue.pop(0)
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx) and visited[nexty][nextx]==-1 and arr[nexty][nextx]!=1):
                visited[nexty][nextx] = visited[y][x]+1
                if(arr[nexty][nextx]!=2):
                    time = visited[nexty][nextx]
                queue.append([nexty, nextx])
    flag = True
    for i in range(N):
        for j in range(N):
            if(arr[i][j] == 0 and visited[i][j]==-1):
                flag = False
                break
        if not flag:
            break
    if flag:
        global answer
        if(answer == -1):
            answer = time
        else:
            answer = min(answer, time)
        

def choose_virus(index, choose_list):
    if(len(choose_list) == M):
        spread_virus(choose_list)
        return
    for i in range(index, len(virus)):
        choose_list.append(i)
        choose_virus(i+1, choose_list)
        choose_list.pop(-1)
    return

if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    virus = []
    for i in range(N):
        new_arr = list(map(int, input().split()))
        for j in range(N):
            if(new_arr[j]==2):
                virus.append([i, j])
        arr.append(new_arr)

    choose_virus(0, [])
    print(answer)