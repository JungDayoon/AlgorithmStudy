import sys
import heapq
input = sys.stdin.readline
T = int(input())
for t in range(T):
    N,D,C = map(int,input().split());C-=1
    answer = 0
    answerCnt = 1
    parent = [[]for _ in range(N)]
    time = [-1 for _ in range(N)]
    for d in range(D):
        a,b,s = map(int,input().split());a-=1;b-=1
        parent[b].append([a,s])
    queue = []
    heapq.heappush(queue,[0,C])
    time[C]=0
    while(len(queue)!=0):
        ti,num= heapq.heappop(queue)
        if time[num]==-1:
            answerCnt+=1
            time[num]=ti
            answer = max(answer,time[a])
        for p in parent[num]:
            a,s = p
            heapq.heappush(queue,[time[num]+s,a])
    print(answerCnt,answer)

