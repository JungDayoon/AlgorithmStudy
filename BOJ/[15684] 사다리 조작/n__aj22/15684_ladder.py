import copy
N, M, H = map(int, input().split())
arr = [[0]*(N-1) for _ in range(H)]
visited = [[True]*(N-1) for _ in range(H)]
min_num = 5
combi_list = []
for i in range(M):
    start,end = map(int, input().split())
    arr[start-1][end-1] = 1
    visited[start-1][end-1] = False
    if(end>1):
        visited[start-1][end-2] = False
    if(end<N-1):
        visited[start-1][end] = False
def check():
    for i in range(0,N-1):
        now_num = i
        for j in range(0, H):
            if(now_num<N-1):
                if(arr[j][now_num] == 1):
                    now_num+=1
                    continue
            if(now_num>0):
                if(arr[j][now_num-1] == 1):
                    now_num-=1
        if(now_num != i):
            return False
    return True

def backtracking(now_num, target, prevx, prevy):
    global min_num
    if(now_num == target):
        if(check()):
            print(target)
            exit()
        return
    if(prevx != len(arr[0])):
        for i in range(prevx, len(arr[0])):
            if(visited[prevy][i]):
                arr[prevy][i] = 1
                visited[prevy][i] = False
                if(i>0):
                    temp1 = visited[prevy][i-1]
                    visited[prevy][i-1] = False
                if(i<N-2):
                    temp2 = visited[prevy][i+1]
                    visited[prevy][i+1] = False
                backtracking(now_num+1, target, i+1, prevy)
                arr[prevy][i] = 0
                visited[prevy][i] = True
                if(i>0):
                    visited[prevy][i-1] = temp1
                if(i<N-2):
                    visited[prevy][i+1] = temp2
    prevx = 0
    if(prevy+1 == len(arr)):
        return
    for i in range(prevy+1, len(arr)):
        for j in range(0, len(arr[0])):
            if(visited[i][j]):
                arr[i][j] = 1
                visited[i][j] = False
                if(j>0):
                    temp1 = visited[i][j-1]
                    visited[i][j-1] = False
                if(j<N-2):
                    temp2 = visited[i][j+1]
                    visited[i][j+1] = False
                backtracking(now_num+1, target, j+1, i)
                arr[i][j] = 0
                visited[i][j] = True
                if(j>0):
                    visited[i][j-1] = temp1
                if(j<N-2):
                    visited[i][j+1] = temp2

for i in range(4):
    backtracking(0, i, 0, 0)
print(-1)
