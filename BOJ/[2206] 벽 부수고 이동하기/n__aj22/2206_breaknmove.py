dx = [0,1,0,-1]
dy = [-1,0,1,0]
def isin(nowx, nowy):
    if(0<=nowx<M and 0<=nowy<N):
        return True
    return False
def findroad():
    queue = []
    queue.append([0, 0, 0])
    answer[0][0][0] = 1
    while(queue):
        nowx, nowy, is_break = queue.pop(0)
        for i in range(4):
            nextx = nowx + dx[i]
            nexty = nowy + dy[i]
            if(isin(nextx, nexty)):
                if(answer[nexty][nextx][is_break] == -1 and road_map[nexty][nextx] == '0'):
                    answer[nexty][nextx][is_break] = answer[nowy][nowx][is_break]+1
                    queue.append([nextx, nexty, is_break])
                elif(is_break == 0 and answer[nexty][nextx][is_break+1] == -1 and road_map[nexty][nextx] == '1'):
                    answer[nexty][nextx][is_break+1] = answer[nowy][nowx][is_break]+1
                    queue.append([nextx, nexty, is_break+1])

if __name__ == "__main__":
    N, M = map(int, input().split())
    endy, endx = N-1, M-1
    road_map = []
    for i in range(N):
        road_map.append(list(input()))
    answer = [[[-1]*2 for _ in range(M)] for _ in range(N)]
    findroad()
    if(answer[endy][endx][0] != -1 and answer[endy][endx][1]!= -1):
        print(min(answer[endy][endx]))
    elif(answer[endy][endx][0]== -1):
        print(answer[endy][endx][1])
    elif(answer[endy][endx][1]== -1):
        print(answer[endy][endx][0])


    
