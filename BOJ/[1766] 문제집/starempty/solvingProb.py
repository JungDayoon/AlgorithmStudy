import heapq

n, m = map(int, input().split())
priority = [[] for _ in range(n+1)]
node = [0 for _ in range(n+1)]
heap = []

for i in range(m):
    a, b = map(int, input().split())
    priority[a].append(b)
    node[b] += 1
#print(priority)
#print(node)
for i in range(1, n+1):  # 시작노드가 없으면
    if node[i] == 0:
        heapq.heappush(heap, i)  # 힙에 넣는다
#print(heap)
answer = []
while heap:
    tmp = heapq.heappop(heap)
    answer.append(tmp)
    for j in priority[tmp]:
        node[j] -= 1
        if node[j] == 0:
            heapq.heappush(heap, j)

for i in answer:
    print(i, end=' ')