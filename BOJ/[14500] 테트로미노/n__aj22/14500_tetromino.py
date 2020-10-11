# 5 5
# 1 2 3 4 5
# 5 4 3 2 1
# 2 3 4 5 6
# 6 5 4 3 2
# 1 2 1 2 1
dr = [-1,1,0,0]
dc = [0,0,-1,1]
N, M = map(int, input().split())
arr = []
visited = [[True]*M for i in range(N)]
max_answer = 0
for i in range(N):
    arr.append(list(map(int, input().split())))

def isIn(nowr, nowc):
    if(0<=nowr<N and 0<=nowc<M and visited[nowr][nowc]): #범위 안에 해당되고, 방문한 적이 없으면
        return True
    return False
def DFS(nowr, nowc, num, num_list):
    global max_answer
    #if(num==4):
    if(len(num_list)==4):
        print(num_list, sum(num_list))
        max_answer = max(max_answer, sum(num_list))
        return



    for i in range(2):
        nextr = nowr + dr[i]
        nextc = nowc + dc[i]

        if(isIn(nextr, nextc)):
            num_list.append(arr[nextr][nextc])
            visited[nextr][nextc] = False
            DFS(nextr, nextc, num+1, num_list)
            num_list.pop(-1)
            visited[nextr][nextc] = True
    return

for i in range(N):
    for j in range(M):
        DFS(i, j, 0, [])
print(max_answer)