import heapq


class Node:
    def __init__(self, price, num, time):
        self.price = price
        self.num = num
        self.time = time

    def __lt__(self, other):
        return self.price < other.price

    def toString(self):
        return str(self.num) + " " + str(self.price) + " " + str(self.time)


inf = 9999999
N = int(input())
T, M = map(int, input().split())
L = int(input())
adj = [[] for _ in range(N)]
Time = [inf for _ in range(N)]
Price = [inf for _ in range(N)]

for _ in range(L):
    s, e, time, price = map(int, input().split())
    adj[s - 1].append(Node(price, e - 1, time))
    adj[e - 1].append(Node(price, s - 1, time))

    #if s < e:
    #    adj[s - 1].append(Node(price, e - 1, time))
    #else:
    #    adj[e - 1].append(Node(price, s - 1, time))

pq = []
heapq.heappush(pq, Node(0, 0, 0))    # 순서대로 price, node num, time
Time[0] = 0
Price[0] = 0

while pq:
    curr = heapq.heappop(pq)

    for Next in adj[curr.num]:
        if curr.price + Next.price > M:
            continue
        if curr.time + Next.time > T:
            continue
        if Price[Next.num] > curr.price + Next.price:
            Price[Next.num] = curr.price + Next.price
            Time[Next.num] = curr.time + Next.time
            heapq.heappush(pq, Node(Price[Next.num], Next.num, Time[Next.num]))
        else:
            # if curr.time + Next.time <= T and curr.price + Next.price <= M:
            heapq.heappush(pq, Node(curr.price + Next.price, Next.num, curr.time + Next.time))

print(Price[N-1] if Price[N-1] <= M else -1)
