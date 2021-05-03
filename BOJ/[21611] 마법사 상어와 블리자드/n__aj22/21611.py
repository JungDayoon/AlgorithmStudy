#위 아래 왼쪽 오른쪽
dy = [0, -1, 1, 0, 0]
dx = [0, 0, 0, -1, 1]
answer = 0
def isin(y, x):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def rotate(arr):
    starty, startx = N//2, N//2
    order = [3, 2, 4, 1]
    now_order = 0
    check, num = 0, 1
    endflag = False
    newarr = []
    while(True):
        
        now_dir = order[now_order]
        for i in range(num):    
            if(starty == 0 and startx == 0):
                endflag = True
                break
            nexty, nextx = starty+dy[now_dir], startx+dx[now_dir]
            if(arr[nexty][nextx]!=0):
                newarr.append(arr[nexty][nextx])
            starty, startx = nexty, nextx
        if(endflag):
            break
        check+=1
        now_order = (now_order+1)%4
        if(check == 2):
            check = 0
            num+=1
    arr = [[0]*N for _ in range(N)]
    starty, startx = N//2, N//2
    order = [3, 2, 4, 1]
    now_order, index = 0, 0
    check, num = 0, 1
    endflag = False
    while(True):
        now_dir = order[now_order]
        for i in range(num):    
            if index == len(newarr):
                endflag = True
                break
            if(starty == 0 and startx == 0):
                endflag = True
                break
            nexty, nextx = starty+dy[now_dir], startx+dx[now_dir]
            arr[nexty][nextx] = newarr[index]
            index+=1
            starty, startx = nexty, nextx
        if(endflag):
            break
        check+=1
        now_order = (now_order+1)%4
        if(check == 2):
            check = 0
            num+=1
    return arr
def break_marble(arr, d, s):
    y, x = N//2, N//2
    for i in range(s):
        nexty, nextx = y+dy[d]*(i+1), x+dx[d]*(i+1)
        if(not isin(nexty, nextx)):
            break
        arr[nexty][nextx] = 0
    return arr

def explosion(arr):
    global answer
    order = [3, 2, 4, 1]
    
    while(True):
        delete_list = []
        starty, startx = N//2, N//2
        now_order = 0
        check, num = 0, 1
        endflag = False
        newarr = []
        now_num = -1
        while(True):
            now_dir = order[now_order]
            for i in range(num):    
                if(starty == 0 and startx == 0):
                    endflag = True
                    break
                nexty, nextx = starty+dy[now_dir], startx+dx[now_dir]
                if arr[nexty][nextx]!= now_num:
                    if(len(newarr)>=4):
                        delete_list.append(newarr)
                    newarr = [[nexty, nextx]]
                    now_num = arr[nexty][nextx]
                else:
                    newarr.append([nexty, nextx])
                starty, startx = nexty, nextx
            if(endflag):
                break
            check+=1
            now_order = (now_order+1)%4
            if(check == 2):
                check = 0
                num+=1
        if len(delete_list) == 0:
            break
        else:
            for i in delete_list:
                marble_num = arr[i[0][0]][i[0][1]]
                answer+=len(i)*marble_num

                for j in i:
                    y, x = j
                    arr[y][x] = 0
        arr = rotate(arr)
    return arr
def change_marble(arr):
    starty, startx = N//2, N//2
    order = [3, 2, 4, 1]
    now_order = 0
    check, num = 0, 1
    endflag = False
    changearr = []
    newarr = []
    now_num = -1
    while(True):
        now_dir = order[now_order]
        for i in range(num):    
            if(starty == 0 and startx == 0):
                endflag = True
                break
            nexty, nextx = starty+dy[now_dir], startx+dx[now_dir]
            if arr[nexty][nextx]!= now_num:
                if(now_num != -1):
                    changearr.append(len(newarr))
                    changearr.append(now_num)
                now_num = arr[nexty][nextx]
                newarr = [arr[nexty][nextx]]
            else:
                newarr.append(arr[nexty][nextx])
            if now_num == 0:
                endflag = True
                break
            starty, startx = nexty, nextx
        if(endflag):
            break
        check+=1
        now_order = (now_order+1)%4
        if(check == 2):
            check = 0
            num+=1
    arr = [[0]*N for _ in range(N)]
    starty, startx = N//2, N//2
    now_order, index = 0, 0
    check, num = 0, 1
    endflag = False
    while(True):
        now_dir = order[now_order]
        for i in range(num):    
            if index == len(changearr):
                endflag = True
                break
            if(starty == 0 and startx == 0):
                endflag = True
                break
            nexty, nextx = starty+dy[now_dir], startx+dx[now_dir]
            arr[nexty][nextx] = changearr[index]
            index+=1
            starty, startx = nexty, nextx
        if(endflag):
            break
        check+=1
        now_order = (now_order+1)%4
        if(check == 2):
            check = 0
            num+=1
    return arr
if __name__ == "__main__":
    N, M = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]
    for i in range(M):
        d, s = map(int, input().split())
        board = break_marble(board, d, s)
        board = rotate(board)
        board = explosion(board)
        board = change_marble(board)

    print(answer)