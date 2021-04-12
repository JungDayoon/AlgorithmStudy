import sys
sys.setrecursionlimit(10**4)
dy = [-1, 0, 1]
dx = [1, 1, 1]

def isin(y, x):
    if(0<=y<R and 0<=x<C and arr[y][x] == '.'):
        return True
    return False
def dfs(y, x, visited, cache):
    if(x == C-1):
        return True
    
    for i in range(3):
        nexty, nextx = y+dy[i], x+dx[i]
        if(isin(nexty, nextx) and not visited[nexty][nextx] and cache[nexty][nextx]):
            visited[nexty][nextx] = True
            flag = dfs(nexty, nextx, visited, cache)
            if(flag):
                return True
            visited[nexty][nextx] = False
    cache[y][x] = False
    return False

if __name__ == "__main__":
    R, C = map(int, input().split())
    arr = []
    for i in range(R):
        arr.append(list(input()))
    visited = [[False]*C for _ in range(R)]
    cache = [[True]*C for _ in range(R)]
    answer = 0
    for i in range(R):
        check = dfs(i, 0, visited, cache)
        if(check):
            answer+=1
    print(answer)