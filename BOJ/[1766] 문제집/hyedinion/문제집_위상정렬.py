import heapq
N,M = map(int,input().split())
pre_problem = {}
in_degree = [ 0 for _ in range(N+1)]
queue = []
for i in range(N):
    pre_problem[i+1] = []

for i in range(M):
    A,B = map(int,input().split())
    pre_problem[A].append(B)
    in_degree[B]+=1

for i in range(1,N+1):
    if in_degree[i]==0:
        queue.append(i)
heapq.heapify(queue)

while queue:
    num = heapq.heappop(queue)
    print(num,end=" ")
    for i in pre_problem[num]:
        in_degree[i]-=1
        if in_degree[i]==0:
            heapq.heappush(queue,i)