from collections import deque
import sys
input = lambda :sys.stdin.readline().rstrip()
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
def isin(R, C, y, x):
    if(0<=y<R and 0<=x<C):
        return True
    return False
def startbomb(R, C, bomb, arr, N):
    if(N == 1):
        return arr
    queue = deque()
    queue.append(bomb)
    t = 1
    while(t<N):
        if(t%2 == 1):
            bomb = []
            for i in range(R):
                for j in range(C):
                    if(arr[i][j] == '.'):
                        arr[i][j] = 'O'
                    else:
                        bomb.append([i, j])
        else:
            for b in bomb:
                y, x = b
                arr[y][x] = '.'
                for i in range(4):
                    nexty, nextx = y+dy[i], x+dx[i]
                    if(isin(R, C, nexty, nextx)):
                        arr[nexty][nextx] = '.'
        t+=1
    return arr


if __name__ == "__main__":
    R, C, N = map(int, input().split())
    arr = []
    bomb = []
    for i in range(R):
        new_arr = list(input())
        arr.append(new_arr)
        for j in range(C):
            if new_arr[j] == 'O':
                bomb.append([i, j])
    answer = startbomb(R, C, bomb, arr, N)
    for i in answer:
        print("".join(i))