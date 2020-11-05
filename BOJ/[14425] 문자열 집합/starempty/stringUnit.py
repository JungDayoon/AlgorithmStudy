import sys

n, m = map(int, input().split())
s = {sys.stdin.readline().strip() for _ in range(n)}

answer = 0

for i in range(m):
    # tmp = input()
    # if tmp in s:
    #     answer += 1
    if input() in s:
        answer += 1

print(answer)
