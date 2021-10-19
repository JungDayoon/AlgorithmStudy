dx = [1, 1, -1, -1]
dy = [-1, 1, 1, -1]
answer = [0, 0]
def isin(y, x):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def check(y, x):
    for i in range(4):
        index = 1
        while(True):
            nexty = y+dy[i]*index
            nextx = x+dx[i]*index
            if(not isin(nexty, nextx)):
                break
            check_index = nexty*N + nextx
            if visited[check_index]:
                return False
            index+=1
    return True
def backtracking(index, c, count):
    if N*N-index+1+count<=answer[c] or index >= N*N:
        return 
    answer[c] = max(answer[c], count)
    y, x = index//N, index%N
    
    j = x
    for i in range(y, N):
        while(True):
            if(j>=N):
                break    
            diag_index = i*N+j
            if(not visited[diag_index] and check(i, j) and arr[i][j] == 1):
                visited[diag_index] = True
                backtracking(diag_index, c, count+1)
                visited[diag_index] = False
            j+=2
        j = (c+1)%2 if i%2==0 else c

if __name__ == "__main__":
    N = int(input())
    arr = []
    visited = [False]*(N*N)
    for i in range(N):
        arr.append(list(map(int, input().split())))
    backtracking(0, 0, 0)
    backtracking(1, 1, 0)
    print(sum(answer))