import copy
dy = [-1, -1, 0, 1, 1, 1, 0, -1]
dx = [0, -1, -1, -1, 0, 1, 1, 1]
answer = 0
def isin(y, x):
    if 0<=y<4 and 0<=x<4:
        return True
    return False
def move_fish(arr, info):
    for i in range(1, 17):
        if i not in info.keys():
            continue
        num = i
        y, x, start_direction = info[i]
        direction = start_direction
        while(True):
            nexty, nextx = y+dy[direction], x+dx[direction]
            if(isin(nexty, nextx)):
                if(arr[nexty][nextx]!=-1):
                    arr[nexty][nextx], arr[y][x] = arr[y][x], arr[nexty][nextx]
                    if(arr[y][x]!=0):
                        info[arr[y][x]] = [y, x, info[arr[y][x]][2]]
                    info[num] = [nexty, nextx, direction]
                    break
            direction = (direction+1)%8
            if(direction == start_direction):
                break
    return arr, info
def shark_possible(arr, shark):
    index = 1
    sharky, sharkx, sharkdir = shark
    nextlist = []
    while(True):
        nexty, nextx = sharky+dy[sharkdir]*index, sharkx+dx[sharkdir]*index
        if(not isin(nexty, nextx)):
            return nextlist
        if(arr[nexty][nextx]>0):
            nextlist.append([nexty, nextx])
        index+=1
    return nextlist


def backtracking(arr, info, total_eat, shark):
    sharky, sharkx, sharkdir = shark
    move_fish(arr, info)
    check = shark_possible(arr, shark)
    if len(check) == 0:
        global answer
        answer = max(answer, total_eat)
        return
    arr[sharky][sharkx] = 0
    for i in check:
        y, x = i
        #먹힐 물고기 정보 저장
        eat_fish = arr[y][x]
        fishinfo = info[eat_fish]
        
        #먹기
        #상어 이동
        arr[y][x] = -1
        del info[eat_fish]
        tempinfo = copy.deepcopy(info)
        temparr = copy.deepcopy(arr)
        backtracking(temparr, tempinfo, total_eat+eat_fish, [y, x, fishinfo[2]])

        arr[y][x] = eat_fish
        info[eat_fish] = fishinfo

    temparr[sharky][sharkx] = -1
        
if __name__ == "__main__":
    arr = []
    info = {}

    for i in range(4):
        new_arr = list(map(int, input().split()))
        temp = []
        for j in range(4):
            temp.append(new_arr[j*2]) #번호만 저장
            info[new_arr[j*2]] = [i, j, new_arr[j*2+1]-1] #번호 -> y, x, 방향
        arr.append(temp)
    first_fish = arr[0][0]
    shark_info = info[first_fish]
    del info[first_fish]
    arr[0][0]=-1
    
    backtracking(arr, info, first_fish, shark_info)
    print(answer)