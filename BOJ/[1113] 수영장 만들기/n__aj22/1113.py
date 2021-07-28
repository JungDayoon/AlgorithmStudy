dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0] #상하좌우
def make_pool(visited, arr, y, x):
    queue = []
    queue.append([y, x]) #y, x, height
    visited[y][x] = True
    height = arr[y][x]
    min_top_height = -1
    candidate = [[y, x]]
    flag2 = True
    while(queue):
        y, x = queue.pop(0)

        for d in range(4):
            nexty, nextx = y+dy[d], x+dx[d]
            if(not visited[nexty][nextx]):
                next_height = arr[nexty][nextx]
                if(next_height == height):
                    visited[nexty][nextx] = True
                    queue.append([nexty, nextx])
                    candidate.append([nexty, nextx])
                elif(next_height>height):
                    if(min_top_height == -1):
                        min_top_height = next_height
                    else:
                        min_top_height = min(min_top_height, next_height)
                else:
                    flag2 = False
    if(not flag2):
        return 0
    for can in candidate:
        y, x = can
        arr[y][x] = min_top_height

    return len(candidate)*(min_top_height-height)
def check_pool_possible(arr, y, x):
    for d in range(4):
        nexty, nextx = y+dy[d], x+dx[d]
        if(arr[nexty][nextx] < arr[y][x]): #한 곳이라도 작은곳이 있으면 불가
            return False
    return True

if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    arr.append([0]*(M+2))
    for i in range(N):
        newlist = [0]
        newlist.extend(list(map(int, input())))
        newlist.append(0)
        arr.append(newlist)
    arr.append([0]*(M+2))

    total_num = 0
    while(True):
        flag = False
        for y in range(1, N+1):
            for x in range(1, M+1):
                if(check_pool_possible(arr, y, x)):
                    visited = [[False]*(M+2) for _ in range(N+2)]
                    add_num = make_pool(visited, arr, y, x)
                    if(add_num != 0):
                        flag = True
                        total_num+=add_num
        if(not flag):
            break

    print(total_num)

