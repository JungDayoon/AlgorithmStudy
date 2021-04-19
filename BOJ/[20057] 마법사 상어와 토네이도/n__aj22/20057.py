dy = [0, 1, 0, -1]
dx = [-1, 0, 1, 0]
width = [0, 1]
out_sand = 0
def isin(y, x):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def move_sand(y, x, direction):
    global out_sand
    leftd = (direction+1)%4
    rightd = (direction+3)%4
    backd = (direction+2)%4
    frontd = direction

    total_sand = arr[y][x]
    left_sand = total_sand

    #5%
    move_sand = int(total_sand*0.05)
    left_sand -= move_sand
    nexty, nextx = y+dy[frontd]*2, x + dx[frontd]*2
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += move_sand
    else:
        out_sand += move_sand
    
    #10%
    move_sand = int(total_sand*0.1)
    left_sand -= move_sand
    nexty, nextx = y+dy[frontd]+dy[rightd], x+dx[frontd]+dx[rightd]
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += move_sand
    else:
        out_sand += move_sand
    
    left_sand -= move_sand
    nexty, nextx = y+dy[frontd]+dy[leftd], x+dx[frontd]+dx[leftd]
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += move_sand
    else:
        out_sand += move_sand

    
    #7%
    move_sand = int(total_sand*0.07)
    left_sand -= move_sand
    nexty, nextx = y+dy[rightd], x+dx[rightd]
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += move_sand
    else:
        out_sand += move_sand
    
    left_sand -= move_sand
    nexty, nextx = y+dy[leftd], x+dx[leftd]
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += move_sand
    else:
        out_sand += move_sand

    
    #2%
    move_sand = int(total_sand*0.02)
    left_sand -= move_sand
    nexty, nextx = y+dy[rightd]*2, x+dx[rightd]*2
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += move_sand
    else:
        out_sand += move_sand
    
    left_sand -= move_sand
    nexty, nextx = y+dy[leftd]*2, x+dx[leftd]*2
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += move_sand
    else:
        out_sand += move_sand
        
    
    #1%
    move_sand = int(total_sand*0.01)
    left_sand -= move_sand
    nexty, nextx = y+dy[rightd]+dy[backd], x+dx[rightd]+dx[backd]
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += move_sand
    else:
        out_sand += move_sand
    
    left_sand -= move_sand
    nexty, nextx = y+dy[leftd]+dy[backd], x+dx[leftd]+dx[backd]
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += move_sand
    else:
        out_sand += move_sand
    
    nexty, nextx = y + dy[frontd], x + dx[frontd]
    if(isin(nexty, nextx)):
        arr[nexty][nextx] += left_sand
    else:
        out_sand+=left_sand
    arr[y][x] = 0
def start_tornado(index):
    y, x = index, index
    direction = 0
    now_width = 1
    w = 0
    flag = False
    while(True):
        for i in range(now_width):
            y, x = y + dy[direction], x + dx[direction]
            move_sand(y, x, direction)
            if(y == 0 and x == 0):
                flag = True
                break
        if(flag):
            break
        now_width += width[w]
        direction = (direction+1)%4
        w = (w+1)%2


if __name__ == "__main__":
    N = int(input())
    arr = []
    for i in range(N):
        arr.append(list(map(int, input().split())))
    startindex = N//2
    start_tornado(startindex)
    print(out_sand)