horse_x = [1,2,2,1,-1,-2,-2,-1]
horse_y = [-2,-1,1,2,2,1,-1,-2]

dx = [0,1,0,-1]
dy = [-1,0,1,0]
min_visit = float('inf')

def isin(x, y):
    if(0<=x<W and 0<=y<H):
        if(arr[y][x]!=1):
            return True
    return False

def monkey(N):
    queue = []
    queue.append([0, 0, N, 0])#y, x, N
    visited[0][0][N] = False
    while(queue):
        node = queue.pop(0)
        nowy = node[0]
        nowx = node[1]
        nowN = node[2]
        nowcount = node[3]
        if(nowy == H-1 and nowx == W-1):
            global min_visit
            min_visit = min(min_visit, nowcount)
            continue
        for i in range(4):
            nexty = nowy+dy[i]
            nextx = nowx+dx[i]
            if(isin(nextx, nexty) and visited[nexty][nextx][nowN] and min_visit>nowcount):
                visited[nexty][nextx][nowN] = False
                queue.append([nexty, nextx, nowN, nowcount+1])
        if(nowN>0):
            for i in range(8):
                nexty = nowy+horse_y[i]
                nextx = nowx+horse_x[i]
                if(isin(nextx, nexty) and visited[nexty][nextx][nowN-1] and min_visit>nowcount):
                    visited[nexty][nextx][nowN-1] = False
                    queue.append([nexty, nextx, nowN-1, nowcount+1])

if __name__ == "__main__":
    K = int(input())
    W, H = map(int, input().split())
    arr = []
    for i in range(H):
        arr.append(list(map(int, input().split())))
    visited = [[[True]*31 for i in range(W)] for j in range(H)]
    monkey(K)
    if(min_visit == float('inf')):
        print(-1)
    else:
        print(min_visit)

    
