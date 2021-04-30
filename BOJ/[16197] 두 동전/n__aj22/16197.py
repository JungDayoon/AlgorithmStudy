import copy
dy = [0, 0, -1, 1]
dx = [-1, 1, 0, 0]
answer = -1
def isin(y, x):
    if 0<=y<N and 0<=x<M:
        return True
    return False

def backtracking(count, now_coin):
    if(count>10 or len(now_coin) == 0):
        return
    if(len(now_coin) == 1):
        global answer
        if(answer == -1):
            answer = count
        else:
            answer = min(answer, count)
        return
    
    for k in range(4):
        new_coin = []
        for i in range(len(now_coin)):
            nexty, nextx = now_coin[i][0]+dy[k], now_coin[i][1]+dx[k]
            if(isin(nexty, nextx)):
                if arr[nexty][nextx] == '.':
                    new_coin.append([nexty, nextx])
                else:
                    new_coin.append(now_coin[i])
        
        backtracking(count+1, new_coin)

    return

if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    coin = []
    for i in range(N):
        new_arr = list(input())
        for j in range(M):
            if new_arr[j] == 'o':
                coin.append([i,j])
                new_arr[j] = '.'
        arr.append(new_arr)
    
    backtracking(0, coin)
    print(answer)

