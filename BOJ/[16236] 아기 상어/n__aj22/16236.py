dy = [-1, 0, 0, 1]
dx = [0, -1, 1, 0]
def isin(y, x):
    if(0<=y<N and 0<=x<N):
        return True
    return False

def find_next_fish(y, x, size):
    queue = []
    queue.append([y, x])
    visited = [[-1]*N for _ in range(N)]
    visited[y][x] = 0
    findy, findx, finddis = -1, -1, -1
    while(queue):
        y, x = queue.pop(0)
        dis = visited[y][x]
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx) and visited[nexty][nextx] == -1):
                if(arr[nexty][nextx] > size):
                    continue
                elif(arr[nexty][nextx] == size or arr[nexty][nextx] == 0):
                    visited[nexty][nextx] = dis + 1
                    queue.append([nexty, nextx])
                else:
                    if(findy == -1):
                        findy, findx, finddis = nexty, nextx, dis+1
                    else:
                        if dis+1>finddis:
                            return findy, findx, finddis
                        else:
                            if nexty<findy:
                                findy, findx, finddis = nexty, nextx, dis+1
                            elif nexty == findy:
                                if(nextx<findx):
                                    findy, findx, finddis = nexty, nextx, dis+1
                    visited[nexty][nextx] = dis + 1
                    queue.append([nexty, nextx])
                    

    return findy, findx, finddis


def start_eat(y, x):
    shark_size = 2
    eat_num = 0
    t = 0
    while(True):
        eaty, eatx, dis = find_next_fish(y, x, shark_size)
        if(eaty == -1):
            break
        arr[eaty][eatx] = 9
        arr[y][x] = 0
        y, x = eaty, eatx
        eat_num+=1
        if(eat_num == shark_size):
            shark_size+=1
            eat_num = 0
        t+=dis
    return t
if __name__ == "__main__":
    N = int(input())
    arr = []
    shark_y, shart_x = 0,0
    for i in range(N):
        new_list = list(map(int, input().split()))
        arr.append(new_list)
        for j in range(N):
            if new_list[j] == 9:
                shark_y, shark_x = i, j
    
    print(start_eat(shark_y, shark_x))

