from copy import deepcopy
def bfs(queue):
    new_queue = deepcopy(queue)
    for k,v in queue.items():
        if v[0]==-1:
            new_queue[k][1]-=1
            if new_queue[k][1]==0:
                del new_queue[k]
        elif v[0]==0:
            move_cell(k,v[1],new_queue)
            if v[1]==1:
                del new_queue[k]
            else:
                new_queue[k] = [-1,v[1]-1]
        else:
            new_queue[k][0]-=1
    return new_queue

def move_cell(k,v,new_queue):
    I,J = map(int,k.split())
    for d in D:
        newI,newJ = I+d[0],J+d[1]
        key = str(newI)+" "+str(newJ)
        if key not in check.keys():
            check[key] = True
            new_queue[key] = [v,v]
    return

T = int(input())
D = [[-1,0],[1,0],[0,-1],[0,1]]#상하좌우
for t in range(T):
    N,M,K = map(int,input().split())
    queue = {}
    check = {}
    for i in range(N):
        ip = list(map(int,input().split()))
        for j in range(M):
            key = str(i)+" "+str(j)
            if ip[j]!=0:
                queue[key] = [ip[j],ip[j]]
                check[key] = True
    for k in range(K):
        queue = bfs(queue)
    print("#{} {}".format(t+1,len(queue)))