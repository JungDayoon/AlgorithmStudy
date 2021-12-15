import sys
import heapq
input = sys.stdin.readline

def check_answer(time):
    answer = 0
    answerCnt = 0
    for i in time:
        if i!=float('inf'):
            answerCnt+=1
            answer = max(answer,i)
    print(answerCnt,answer)
    return

def dijkstra(edge,distance,C):
    queue = []
    heapq.heappush(queue,[0,C])
    distance[C]=0

    while(len(queue)!=0):
        cur_time, cur_node = heapq.heappop(queue)
        if cur_time>distance[cur_node]:#탐색할 node의 현재 비용보다 queue에 기록된 비용이 크면 업데이트 할 필요가 없음
            continue
        for next_node,second in edge[cur_node]: # 탐색할 node에 연결된 node들을 탐색
            next_time = distance[cur_node]+second
            if next_time<distance[next_node]: # 연결된 node를 현재비용보다 작은 비용으로 갈 수 있다면 업데이트
                distance[next_node] = next_time
                heapq.heappush(queue,[next_time,next_node])
    return

T = int(input())
for t in range(T):
    N,D,C = map(int,input().split());C-=1
    edge = [[]for _ in range(N)]
    distance = [float('inf') for _ in range(N)]
    for d in range(D):
        a,b,s = map(int,input().split());a-=1;b-=1
        edge[b].append([a,s])

    dijkstra(edge,distance,C)
    check_answer(distance)