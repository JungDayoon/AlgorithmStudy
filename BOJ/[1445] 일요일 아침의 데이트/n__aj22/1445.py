import heapq
dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]
def isin(y, x):
    if(0<=y<N and 0<=x<M):
        return True
    return False
def dilkstra(starty, startx, check):
    queue = []
    distances = [[[float('inf'), float('inf')]]*M for _ in range(N)]
    distances[starty][startx] = [0, 0] #쓰레기, 쓰레기 옆길 

    heapq.heappush(queue, [0, 0, starty, startx])

    while(queue):
        trash1, trash2, y, x = heapq.heappop(queue)

        if(trash1<distances[y][x][0] and trash2<distances[y][x][1]):
            continue

        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx)):
                next_trash1, next_trash2 = trash1, trash2
                if arr[nexty][nextx] == 'g':
                    next_trash1+=1
                elif check[nexty][nextx] :
                    next_trash2+=1
                
                if(distances[nexty][nextx][0]>next_trash1):
                    distances[nexty][nextx] = [next_trash1, next_trash2]
                    heapq.heappush(queue, [next_trash1,next_trash2,nexty, nextx])
                elif(distances[nexty][nextx][0]==next_trash1 and distances[nexty][nextx][1]>next_trash2):
                    distances[nexty][nextx][1] = next_trash2
                    heapq.heappush(queue, [next_trash1,next_trash2,nexty, nextx])   
    return distances
def next_to_trash(trash):
    check = [[False]*M for _ in range(N)]
    for i in trash:
        y, x = i
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx)):
                check[nexty][nextx] = True
    check[endy][endx] = False
    return check    
if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    starty, startx, endy, endx = 0, 0, 0, 0
    trash = []
    for i in range(N):
        new_arr = list(input())
        for j in range(M):
            if(new_arr[j] == 'S'):
                starty, startx = i, j
            if(new_arr[j] == 'F'):
                endy, endx = i, j
            if(new_arr[j] == 'g'):
                trash.append([i, j])
        arr.append(new_arr)
    
    check = next_to_trash(trash)
    answer = dilkstra(starty, startx, check)[endy][endx]
    print(answer[0], answer[1])