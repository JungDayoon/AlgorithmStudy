import copy
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
def isin(y, x):
    if(0<=y<R and 0<=x<C):
        return True
    return False
def spread():
    temp = [[0]*C for _ in range(R)]

    for i in range(R):
        for j in range(C):
            if(arr[i][j]>0):
                divided_num = arr[i][j]//5
                spread_num = 0
                for k in range(4):
                    nexty, nextx = i+dy[k], j+dx[k]
                    if(isin(nexty, nextx) and arr[nexty][nextx]!=-1):
                        spread_num+=1
                        temp[nexty][nextx]+=divided_num
                
                arr[i][j] -= (divided_num*spread_num)
    for i in range(R):
        for j in range(C):
            arr[i][j]+=temp[i][j]
    return
def air_fresh():
    up_y = air_fresher[0]
    down_y = air_fresher[1]

    direction_start = 1
    before = 0

    y, x = up_y, 0
    while(True):
        nexty, nextx = y+dy[direction_start], x+dx[direction_start]
        if(not isin(nexty, nextx)):
            direction_start = (direction_start+3)%4
            nexty, nextx = y+dy[direction_start], x+dx[direction_start]
        if(arr[nexty][nextx] == -1):
            break
        after = arr[nexty][nextx]
        arr[nexty][nextx] = before
        before = after
        y, x = nexty, nextx
    y, x = down_y, 0
    direction_start = 1
    before = 0
    while(True):
        nexty, nextx = y+dy[direction_start], x+dx[direction_start]
        if(not isin(nexty, nextx)):
            direction_start = (direction_start+1)%4
            nexty, nextx = y+dy[direction_start], x+dx[direction_start]
        if(arr[nexty][nextx] == -1):
            break
        after = arr[nexty][nextx]
        arr[nexty][nextx] = before
        before = after
        y, x = nexty, nextx
    return
def start():
    for t in range(T):
        #확산
        spread()
        #공기 청정기 
        air_fresh()
    answer = 0 
    for i in range(R):
        for j in range(C):
            if(arr[i][j]!=-1):
                answer+=arr[i][j]
    return answer       
if __name__ == "__main__":
    R, C, T = map(int, input().split())
    air_fresher = []
    arr = []
    for i in range(R):
        new_list = list(map(int, input().split()))
        for j in range(C):
            if new_list[j] == -1:
                air_fresher.append(i)
        arr.append(new_list)
    print(start())
