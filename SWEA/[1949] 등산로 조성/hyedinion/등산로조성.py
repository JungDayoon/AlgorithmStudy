def find_hill(hill_num,hill_list):
    for i in range(N):
        for j in range(N):
            if mountain[i][j]>hill_num:
                hill_num = mountain[i][j]
                hill_list = [[i,j]]
            elif mountain[i][j]==hill_num:
                hill_list.append([i,j])
    return [hill_num,hill_list]

def dfs(I,J,depth,curve):
    global answer
    for d in D:
        newI,newJ = I+d[0],J+d[1]
        if 0<=newI<N and 0<=newJ<N and check[newI][newJ]:
            if mountain[I][J]>mountain[newI][newJ]:
                check[newI][newJ] = False
                dfs(newI,newJ,depth+1,curve)
                check[newI][newJ] = True
            elif curve:
                for k in range(1,K+1):
                    mountain[newI][newJ]-=k
                    check[newI][newJ] = False
                    if mountain[I][J]>mountain[newI][newJ]:
                        dfs(newI,newJ,depth+1,False)
                    else:
                        answer = max(answer,depth)
                    mountain[newI][newJ]+=k
                    check[newI][newJ] = True
            else:
                answer = max(answer,depth)

    return

T = int(input())
D = [[-1,0],[1,0],[0,-1],[0,1]]#상하좌우
for t in range(T):
    N,K = map(int,input().split())
    mountain = [ list(map(int,input().split())) for _ in range(N) ]
    hill_num , hill_list = find_hill(0,[])
    check = [ [True]*N for _ in range(N)]
    answer = 0

    
    for h in hill_list:
        check[h[0]][h[1]] = False
        dfs(h[0],h[1],1,True)
        check[h[0]][h[1]] = True
    print('#{} {}'.format(t+1,answer))