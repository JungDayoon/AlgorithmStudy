dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
def isin(y, x):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def start_move(sharks, shark_num, shark_dir):
    arr = [[[-1, -1]]*N for _ in range(N)]#뿌린 시간, 번호 
    for i in range(shark_num):
        y, x = sharks[i]
        arr[y][x] = [0, i]
    for t in range(1, 1002):
        #움직여주기 
        if(shark_num == 1):
            return t-1
        new_sharks = []
        new_dir = []
        for shark_index in range(len(sharks)):
            if(sharks[shark_index]!=[-1, -1]):
                sharky, sharkx = sharks[shark_index]
                find = False
                for i in shark_pri[shark_index][shark_dir[shark_index]]:#인접한 칸 중 빈칸
                    nexty = sharky+dy[i]
                    nextx = sharkx+dx[i]
                    if(isin(nexty, nextx)):
                        if(arr[nexty][nextx] == [-1, -1]):
                            new_sharks.append([nexty, nextx])
                            new_dir.append(i)
                            find = True
                            break
                if(not find):#아직 못찾았으면 
                    for i in shark_pri[shark_index][shark_dir[shark_index]]:#인접한 칸 중 빈칸
                        nexty = sharky+dy[i]
                        nextx = sharkx+dx[i]
                        if(isin(nexty, nextx)):
                            if(arr[nexty][nextx][1] == shark_index):
                                new_dir.append(i)
                                new_sharks.append([nexty, nextx])
                                find = True
                                break
            else:
                new_sharks.append([-1, -1])
                new_dir.append(-1)
        for i in range(N):
            for j in range(N):
                if(t-arr[i][j][0] == k):
                    arr[i][j] = [-1, -1]
        for new_shark_index in range(len(new_sharks)):
            if(new_sharks[new_shark_index]!=[-1, -1]):
                if(new_sharks[new_shark_index] not in sharks[0:new_shark_index]):
                    i = new_sharks[new_shark_index][0]
                    j = new_sharks[new_shark_index][1]
                    arr[i][j] = [t, new_shark_index]
                    sharks[new_shark_index] = [i, j]
                    shark_dir[new_shark_index] = new_dir[new_shark_index]
                else:
                    shark_num -=1
                    sharks[new_shark_index] = [-1, -1]
    return -1
if __name__ == "__main__":
    N, M, k = map(int, input().split())
    sharks = {}
    arr = []
    for i in range(N):
        new_map = list(map(int, input().split()))
        arr.append(new_map)
        for j in range(N):
            if(new_map[j]!=0):
                sharks[new_map[j]] = [i, j]
    sorted_shark = []
    for i in range(1, M+1):
        sorted_shark.append(sharks[i])

    shark_dir = list(map(int, input().split()))
    shark_pri = []
    for i in range(M):
        shark_dir[i]-=1
        new_shark = []
        for j in range(4):
            up, down, left, right = map(int, input().split())
            new_shark.append([up-1, down-1, left-1, right-1])
        shark_pri.append(new_shark)

    print(start_move(sorted_shark, M, shark_dir))



