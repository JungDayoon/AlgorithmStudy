direction = {'E':[0, 1], 'S':[1, 0], 'W':[0, -1], 'N':[-1, 0]}
check = []
answer = 0
def isin(y, x):
    if(0<=y<N and 0<=x<M):
        return True
    return False
def init():
    global check
    check = [['S']*M for _ in range(N)]
    return
def start_round(attack, depence):
    global answer
    y = int(attack[0])-1
    x = int(attack[1])-1
    queue = []
    queue.append([y, x, arr[y][x]])
    now_direction = attack[2]
    if check[y][x]!='F':
        check[y][x] = 'F'
        answer+=1
    while(queue):
        y, x, now_distance = queue.pop(0)
        
        for i in range(1, now_distance):
            nexty, nextx = y+direction[now_direction][0]*i, x+direction[now_direction][1]*i
            if(isin(nexty, nextx)):
                if(check[nexty][nextx]!='F'):
                    check[nexty][nextx] = 'F'
                    answer+=1
                    queue.append([nexty, nextx, arr[nexty][nextx]])
            else:
                break
        
    check[int(depence[0])-1][int(depence[1])-1] = 'S'
    return
if __name__ == "__main__":
    N, M, R = map(int, input().split())
    arr = []
    init()
    for i in range(N):
        arr.append(list(map(int, input().split())))
    round_list = []
    for i in range(2*R):
        round_list.append(list(input().split()))
    
    for i in range(R):
        start_round(round_list[i*2], round_list[i*2+1])
    print(answer)
    for i in check:
        print(' '.join(i))

    
    