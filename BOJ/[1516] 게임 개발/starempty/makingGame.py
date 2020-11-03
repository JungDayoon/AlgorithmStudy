from collections import deque

num = int(input())
num += 1
board = [[] for _ in range(num)]
time = ['dummy']
ind = [0]*num
answer = [0]*num

for i in range(1, num):
    tmp = list(map(int, input().split()))
    time.append(tmp[0])
    for j in tmp[1:-1]:
        ind[i] += 1
        board[j].append(i)

# print(board)
# print(time)
# print(ind)

q = deque()
for i in range(1, num):
    if ind[i] == 0:
        q.append(i)

while q:
    cur = q.popleft()
    answer[cur] += time[cur]
    for i in board[cur]:
        ind[i] -= 1
        answer[i] = max(answer[i], answer[cur])
        if ind[i] == 0:
            q.append(i)

for i in answer[1:]:
    print(i)