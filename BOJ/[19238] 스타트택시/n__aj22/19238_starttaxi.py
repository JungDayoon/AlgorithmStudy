N, M, K = map(int, input().split())
arr1 = [[0]*N for _ in range(N)]#사람
arr2 = [[0]*N for _ in range(N)]#도착지
map1 = [[0]*N for _ in range(N)]
di = [-1,0,0,1]
dj = [0,-1,1,0]

def closestperson(nowi,nowj):
    queue = [[nowi,nowj,0]]
    besti = 9999
    bestj = 9999
    bestmove = 500001
    visited = [[0]*N for _ in range(N)]
    visited[nowi][nowj] = 1
    while(queue):
        vertex = queue.pop(0)
        ni = vertex[0]
        nj = vertex[1]
        nmove = vertex[2]
        if(nmove>bestmove):
            break
        if(arr1[ni][nj]>0): # 사람이 존재한다면
            if(bestmove>=nmove): #움직임이 적으면
                if(besti>ni):
                    besti = ni
                    bestj = nj
                    bestmove = nmove
                elif(besti == ni):
                    if(bestj>nj):
                        bestj = nj
                        bestmove = nmove
        for i in range(4):
            nexti = ni + di[i]
            nextj = nj + dj[i]
            if(nexti!=-1 and nextj != -1 and nexti !=N and nextj != N):
                if(visited[nexti][nextj] == 0 and map1[nexti][nextj] == 0):
                    visited[nexti][nextj] = 1
                    queue.append([nexti, nextj, nmove+1])
    return besti, bestj, bestmove
def riding(nowi, nowj, endi, endj):
    queue = [[nowi, nowj, 0]]
    visited = [[0] * N for _ in range(N)]
    visited[nowi][nowj] = 1
    while(queue):
        vertex = queue.pop(0)
        ni = vertex[0]
        nj = vertex[1]
        nmove = vertex[2]
        if(ni == endi and nj == endj):#찾으려는 목적지이면
            return ni, nj, nmove
        for i in range(4):
            nexti = ni + di[i]
            nextj = nj + dj[i]
            if (nexti != -1 and nextj != -1 and nexti != N and nextj != N):
                if (visited[nexti][nextj] == 0 and map1[nexti][nextj] == 0):
                    visited[nexti][nextj] = 1
                    queue.append([nexti, nextj, nmove + 1])

    return -1, -1, 500001

def start_taxi(taxi_i, taxi_j, person_info, num):
    global K
    global M
    while(M>=num):
        nexti, nextj, move = closestperson(taxi_i, taxi_j)
        if(move == 500001):
            return -1
        if(move>=K):
            return -1
        K -= move #연료 감소
        #승객 위치로 택시 이동
        taxi_i = nexti
        taxi_j = nextj
        personnum = arr1[taxi_i][taxi_j] #태운 승객 번호
        arr1[taxi_i][taxi_j] = 0
        endi = person_info[personnum-1][0]
        endj = person_info[personnum-1][1]
        nexti, nextj, move = riding(taxi_i, taxi_j, endi, endj)
        if(move == 500001):
            return -1
        if(move>K):
            return -1
        K += move
        taxi_i = nexti
        taxi_j = nextj
        num+=1

    return K

for i in range(N):
    new_map = list(map(int, input().split()))
    for j in range(N):
        if(new_map[j]==1):
            map1[i][j] = 1

starti, startj = map(int, input().split())
starti-=1
startj-=1
person_info = []
for i in range(M):
    n1, n2, m1, m2 = map(int, input().split())
    person_info.append([m1-1, m2-1])
    arr1[n1-1][n2-1] = i+1
    arr2[m1-1][m2-1] = i+1
print(start_taxi(starti, startj, person_info, 1))

