import sys
input = sys.stdin.readline

N,M = map(int,input().split())
Sset = {input() for _ in range(N)}
answer = 0

for i in range(M):
    if input() in Sset:
        answer+=1

print(answer)