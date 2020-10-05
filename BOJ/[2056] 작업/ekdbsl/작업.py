T = int(input())
adj = [[] for _ in range(T+1)]
queue = []
time = [0 for _ in range(T+1)]
indegree = [0 for _ in range(T+1)]
endTime = [0 for _ in range(T+1)]
result = []

for t in range(1, T+1):
    process = [int(x) for x in input().split()]
    time[t] =  process[0]
    if process[1] == 0:
        queue.append(t)
        endTime[t] = time[t]
    else:
        for i in range(process[1]):
            adj[process[2+i]].append(t)
            indegree[t] += 1

while queue:
    curr = queue.pop(0)
    result.append(curr)

    for Next in adj[curr]:
        indegree[Next] -= 1
        endTime[Next] = max(endTime[Next], endTime[curr] + time[Next])
        if indegree[Next] == 0:
            queue.append(Next)
    
print(max(endTime))