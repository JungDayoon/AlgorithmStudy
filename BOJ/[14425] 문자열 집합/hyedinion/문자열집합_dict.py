import sys
input = sys.stdin.readline

N,M = map(int,input().split())
Sdict = {}
for i in range(N):
    Sdict[input()[:-1]] = True
temp = [input()[:-1] for _ in range(M)]
answer = 0

for i in temp:
    if i in Sdict.keys():
        answer+=1

print(answer)