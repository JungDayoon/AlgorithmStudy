dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
def isin(y, x):
    if(0<=y<N and 0<=x<M):
        return True
    return False
def choose_cheeze(arr):
    visited = [[False]*M for _ in range(N)]
    delete_cheeze = []
    queue = []
    queue.append([0, 0])
    visited[0][0] = True
    while(queue):
        y, x = queue.pop(0)
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx)):
                if(arr[nexty][nextx] == 1):
                    if([nexty, nextx] not in delete_cheeze):
                        delete_cheeze.append([nexty, nextx])
                if(not visited[nexty][nextx] and arr[nexty][nextx] == 0):
                    visited[nexty][nextx] = True
                    queue.append([nexty, nextx])
    
    return delete_cheeze

if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    cheeze = 0
    for i in range(N):
        new_arr = list(map(int, input().split()))
        cheeze+=new_arr.count(1)
        arr.append(new_arr)
    t = 0
    while(True):
        t+=1
        #지울 치즈 선택
        delete_cheeze_list = choose_cheeze(arr)

        #선택된 치즈 삭제 
        for c in delete_cheeze_list:
            y, x = c
            arr[y][x] = 0

        #만약 삭제되고 난 후 개수가 0이라면 
        delete_num = len(delete_cheeze_list)
        if(cheeze-delete_num == 0):
            print(t)
            print(cheeze)
            break
        else:
            cheeze-=delete_num
    

