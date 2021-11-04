from collections import deque
T = int(input())
for t in range(T):
    answer = 0
    N,M,R,C,L = map(int,input().split())
    tunnelMap = [list(map(int,input().split())) for _ in range(N)]
    D = [[-1,0],[1,0],[0,-1],[0,1]]
    tunnel = {0:[], 1:[0,1,2,3], 2:[0,1], 3:[2,3], 4:[0,3], 5:[1,3], 6:[1,2], 7:[0,2]}
    check = [[True]*M for _ in range(N)]
    check[R][C]=False
    queue = [[R,C]]

    if tunnelMap[R][C]!=0:
        answer+=1
        for l in range(1,L):
            new_queue = []
            for q in queue:
                r,c=q
                direction = tunnelMap[r][c]
                for tn in tunnel[direction]:
                    newR,newC = D[tn][0]+r, D[tn][1]+c
                    if 0<=newR<N and 0<=newC<M and (((tn+1)%2+(tn//2)*2) in tunnel[tunnelMap[newR][newC]]) and check[newR][newC]:
                        answer+=1
                        check[newR][newC]=False
                        new_queue.append([newR,newC])
            queue=new_queue[:]
    print("#{} {}".format(t+1,answer))

#tunnel번호에 해당하는 이동경로로 모두 이동한다.
# i,j 에서 한칸 이동할 수 있는 경로에  True 이면 check=False,answer+1
# 다음 갈 수 있는 nextI,nextJ를 deque에 저장.

#놓친것
#같은 파이프 방향으로 놓여져 있는곳에만 갈 수 있음
# 상->하 하->상 좌->우 우->좌 방향으로 연결되어 있어야 함