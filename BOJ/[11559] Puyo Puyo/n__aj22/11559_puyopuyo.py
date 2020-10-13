import copy
arr = []
for i in range(12):
    arr.append(list(input()))
change_num = 0
visited = [[True]*6 for _ in range(12)]
dr = [-1,0,1,0]
dc = [0,1,0,-1]
def isIn(nowr, nowc):
    if(0<=nowr<12 and 0<=nowc<6):
        if(visited[nowr][nowc] and arr[nowr][nowc]!='.'):
            return True
    return False

def DFS(now_char, nowr, nowc):
    global change_num
    change_num+=1
    visited[nowr][nowc] = False
    for i in range(4):
        nextr = nowr + dr[i]
        nextc = nowc + dc[i]
        if(isIn(nextr, nextc) and arr[nextr][nextc]==now_char):
            DFS(now_char, nextr, nextc)
    return

num = 0
while(True):
    # 공통된 것 찾아주
    flag = 0
    for i in range(12):
        for j in range(6):
            if(arr[i][j]!='.'):
                change_num = 0
                temp_visited = copy.deepcopy(visited)
                DFS(arr[i][j], i, j)
                if(change_num<4):
                    visited = temp_visited

    #4개 이상인 공통된 것 제거하기
    for i in range(6):
        for j in range(12):
            if(visited[j][i] == False):
                for k in range(j, 0,-1):
                    arr[k][i] = arr[k-1][i]
                arr[0][i] = '.'
                visited[j][i] = True
                flag = 1


    if(flag == 0):
        break
    num+=1
print(num)

