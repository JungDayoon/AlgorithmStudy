
# import sys
# sys.stdin = open("/Users/najihye/algo2/SWEA/[5650] 핀볼 게임/n__aj22/sample_input.txt", "r")
dx = [0,1,0,-1]
dy = [-1,0,1,0]
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
score = 0
N = 0
def wormhole(arr):
    hole_info = {}
    for i in range(N):
        for j in range(N):
            if(arr[i][j]>5):
                if(arr[i][j] in hole_info):
                    hole_info[arr[i][j]].append([i,j])
                else:
                    hole_info[arr[i][j]] = [[i,j]]
    return hole_info
def isin(x, y):
    if(0<=x<N and 0<=y<N):
        return True
    return False
def change_dir(direction, block_num):
    dirlist = [2,3,0,1]

    if(block_num == 1):
        dirlist = [2,3,1,0]
    elif(block_num == 2):
        dirlist = [1,3,0,2]
    elif(block_num == 3):
        dirlist = [3,2,0,1]
    elif(block_num == 4):
        dirlist = [2,0,3,1]
    
    return dirlist[direction]
def find_other_wormhole(wormhole_info, num, x, y):
    this_wormhole = wormhole_info[num]
    if(this_wormhole[0] == [y, x]):
        return this_wormhole[1]
    else:
        return this_wormhole[0]

def start(arr, nowx, nowy, startx, starty, count, direction, wormhole_info, startflag):
    if(isin(nowx, nowy)==False):
        direction = change_dir(direction, 0)
        nexty = nowy+dy[direction]
        nextx = nowx+dx[direction]
        start(arr, nextx, nexty, startx, starty, count+1, direction, wormhole_info, startflag)    
        return 

    if((startx == nowx and starty == nowy and startflag == 1) or (arr[nowy][nowx] == -1)):
        global score
        score = max(score, count)
        return
    startflag = 1
    block_num = arr[nowy][nowx]
    if(block_num == 0):
        nexty = nowy+dy[direction]
        nextx = nowx+dx[direction]
        start(arr, nextx, nexty, startx, starty, count, direction, wormhole_info, startflag)
    elif(0<block_num<6):
        direction = change_dir(direction, block_num)
        nexty = nowy+dy[direction]
        nextx = nowx+dx[direction]
        start(arr, nextx, nexty, startx, starty, count+1, direction, wormhole_info, startflag)
    elif(5<block_num):
        other_wormhole = find_other_wormhole(wormhole_info, block_num, nowx, nowy)
        nexty = other_wormhole[0]+dy[direction]
        nextx = other_wormhole[1]+dx[direction]
        start(arr, nextx, nexty, startx, starty, count, direction, wormhole_info, startflag)


def start2(arr, nowx, nowy, startx, starty, count, direction, wormhole_info, startflag):
    count = 0
    while(True):
        if(isin(nowx, nowy)==False):#경계를 벗어난 경우
            direction = change_dir(direction, 0)
            nowy = nowy+dy[direction]
            nowx = nowx+dx[direction]
            count+=1  
            continue

        if((startx == nowx and starty == nowy and startflag == 1) or (arr[nowy][nowx] == -1)):
            global score
            score = max(score, count)
            break
        startflag = 1
        block_num = arr[nowy][nowx]
        if(block_num == 0):
            nowy = nowy+dy[direction]
            nowx = nowx+dx[direction]
        elif(0<block_num<6):
            direction = change_dir(direction, block_num)
            nowy = nowy+dy[direction]
            nowx = nowx+dx[direction]
            count+=1
        elif(5<block_num):
            other_wormhole = find_other_wormhole(wormhole_info, block_num, nowx, nowy)
            nowy = other_wormhole[0]+dy[direction]
            nowx = other_wormhole[1]+dx[direction]
        
    
def choose(arr, wormhole_info):
    
    for i in range(N):
        for j in range(N):
            if(arr[i][j] == 0):
                for direction in range(4):
                    # print(j, i, direction)
                    start2(arr, j, i, j, i, 0, direction, wormhole_info, 0)
                    # 점수 계산 호출

for test_case in range(1, T + 1):
    N = int(input())
    arr = []
    score = 0
    for i in range(N):
        arr.append(list(map(int, input().split())))
    wormhole_list = wormhole(arr)
    # start(arr, 0, 0, 0, 0, 0, 2, wormhole_list, 0)
    choose(arr, wormhole_list)
    print("#"+str(test_case)+" "+str(score))


