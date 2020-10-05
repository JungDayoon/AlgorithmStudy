from collections import deque
import operator

pos = [[-1,0],[1,0],[0,-1],[0,1]]
change = [1,-1,1,-1]

def isInDanger(y, x):
    if y==0 or y == N-1 or x==0 or x == N-1:
        return True
    return False

def moveThing(AliveThing, Map):
    for a in AliveThing:
        liveThing = Map[a[0]][a[1]].popleft()
        ny = a[0] + pos[liveThing[1]][0]
        nx = a[1] + pos[liveThing[1]][1]

        Map[ny][nx].append(liveThing)

    for i in range(N):
        for j in range(N):
            if len(Map[i][j]) >0:
                if isInDanger(i, j):
                    if(Map[i][j][0][0] //2 ==0):
                        Map[i][j].popleft()
                    else:
                        Map[i][j][0][0] //= 2
                        Map[i][j][0][1] += change[Map[i][j][0][1]]
                elif len(Map[i][j]) >= 2:
                    tmpList = Map[i][j]
                    total = sum(map(lambda x: int(x[0]), tmpList))
                    # idx, value= max(enumerate(tmpList), key = operator.itemgetter(0)) 
                    value = max(tmpList, key = lambda item: item[0])
                    idx = tmpList.index(value)
                    
                    maxDir = Map[i][j][idx][1]
                    Map[i][j] = deque()
                    Map[i][j].append([total, maxDir])

T = int(input())

for test_case in range(1, T + 1):
    N, M, K = map(int, input().split())
    Map = [[deque() for _ in range(N)]for _ in range(N)]
   
    for k in range(K):
        y, x, cnt, _dir = map(int, input().split())
        Map[y][x].append([cnt, _dir-1])

    for time in range(M):
        AliveThing = []
        for i in range(N):
            for j in range(N):
                if len(Map[i][j]) > 0:
                    AliveThing.append([i, j])

        moveThing(AliveThing, Map)

    count = 0
    for i in range(N):
        for j in range(N):
            if len(Map[i][j])>0:
                count += Map[i][j][0][0]

    print("#{} {}".format(test_case, count))