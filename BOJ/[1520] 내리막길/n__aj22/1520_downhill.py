M, N = map(int, input().split())
check_hill = [[-1]*N for i in range(M)]
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
def isin(x, y):
    if(0<=y<M and 0<=x<N):
        return True
    return False
def findroad(x, y, arr):
    if(x == N-1 and y == M-1):
        return 1
    if(check_hill[y][x]!=-1):
        return check_hill[y][x]
    
    check_hill[y][x] = 0 

    for i in range(4):
        nexty = y + dy[i]
        nextx = x + dx[i]
        if(isin(nextx, nexty) and arr[y][x]>arr[nexty][nextx]):
            check_hill[y][x]+=findroad(nextx, nexty, arr)
    return check_hill[y][x]


if __name__ == "__main__":
    map_hill = []

    for i in range(M):
        map_hill.append(list(map(int, input().split())))
    findroad(0, 0, map_hill)
    print(check_hill[0][0])