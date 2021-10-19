dx = [-1, 0, 1, 0] # 서북동남
dy = [0, -1, 0, 1]
def check_possible_direction(num):
    candidate = []
    divnum = num
    while(True):
        if(divnum<2):
            candidate.append(divnum)
            while(len(candidate)<4):
                candidate.append(0)
            break
        candidate.append(divnum%2)
        divnum//=2

    return candidate
def startdfs(y, x, visited, arr, index):
    cand = check_possible_direction(arr[y][x])
    num = 0
    for i in range(4):
        if(cand[i] == 0):
            nexty, nextx = y+dy[i], x+dx[i]
            if(visited[nexty][nextx] == 0):
                num+=1
                visited[nexty][nextx] = index
                num+=startdfs(nexty, nextx, visited, arr, index)

    return num

if __name__ == "__main__":
    n, m = map(int, input().split())
    arr = []
    room_info = {}
    for i in range(m):
        arr.append(list(map(int, input().split())))
    
    visited = [[0]*n for _ in range(m)]

    index = 1
    max_room = 0
    for i in range(m):
        for j in range(n):
            if(visited[i][j] == 0):
                visited[i][j] = index
                #dfs 시작
                room_size = startdfs(i, j, visited, arr, index)+1
                max_room = max(room_size, max_room)
                room_info[index] = room_size
                index+=1

    max_sum = 0
    for i in room_info.keys():
        roomnum, roomsize = i, room_info[i]
        for y in range(m):
            for x in range(n):
                if(visited[y][x] == roomnum):
                    for k in range(4):
                        nexty, nextx = y+dy[k], x+dx[k]
                        if(0<=nexty<m and 0<=nextx<n and visited[nexty][nextx]!=roomnum):
                            max_sum = max(max_sum, roomsize+room_info[visited[nexty][nextx]])
    print(index-1)
    print(max_room)
    print(max_sum)