import sys
from collections import deque
input = sys.stdin.readline
N,M,Fuel = map(int,input().split())
wall_map = [ list(map(int,input().split())) for _ in range(N)]#평지 : 0 , 벽 : 1 , 승객 탑승: 2
startI,startJ = map(int,input().split()); startI-=1; startJ-=1
guest_dict = {}
for i in range(M):
    guest_startI,guest_startJ,guest_arriveI,guest_arriveJ = map(int,input().split())
    key = str(guest_startI-1)+" "+str(guest_startJ-1)
    guest_dict[key] = [guest_arriveI-1,guest_arriveJ-1]
    wall_map[guest_startI-1][guest_startJ-1] = 2
D = [[-1,0],[1,0],[0,-1],[0,1]]#상하좌우

#최단거리승객 구하기
#bfs
def min_distance(startI,startJ,guest_dict,wall_map,arrive_flag = False,arriveI=None,arriveJ=None,move1=0):
    check_list=[[0]*N for _ in range(N)] #갔던 길인지 확인
    queue = deque([[startI,startJ,0]])
    min_depth = 999 #최단거리 depth
    check_min = [] # 최단거리 승객들 list
    while len(queue)>0:
        i,j,move = queue.popleft()
        if move1+move>Fuel:
            return [-1]

        #도착지로 갈 때
        if arrive_flag:
            if i==arriveI and j==arriveJ:
                return [move1,move,arriveI,arriveJ]
        #승객을 태울 때
        else:
            #같은 depth를 모두 탐색했을 때
            if move>min_depth:
                move -=1
                break
            #승객이 있을 때
            if wall_map[i][j]==2:
                min_depth = move #최단거리 depth설정
                check_min.append([i,j])
        if not check_min:
            for d in D:
                id = i+d[0]
                jd = j+d[1]
                if 0<=id<N and 0<=jd<N and wall_map[id][jd]!=1 and check_list[id][jd]==0:
                    check_list[id][jd] = 1
                    queue.append([id,jd,move+1])

    if len(check_min)!=0:
        check_min.sort()
        i,j = check_min[0]
        wall_map[i][j]=0
        d1,d2 = guest_dict.pop(str(i)+" "+str(j))
        return min_distance(i,j,guest_dict,wall_map,True,d1,d2,move)
    return [-1]

#시작
answer = -1
for m in range(M):
    guest = min_distance(startI,startJ,guest_dict,wall_map)
    #연료가 바닥났거나 갈 수 없는길
    if guest[0]==-1:
        answer = -1
        break
    Fuel += guest[1]-guest[0]
    answer = Fuel
    startI = guest[2]
    startJ = guest[3]

print(answer)