n, d = map(int, input().split())
shortCuts = [[] for _ in range(n)]
movedDistance = [99999 for _ in range(d+1)]
for i in range(n):
    s, e, l = map(int, input().split())
    shortCuts[i] = (s, e, l)

shortCuts.sort()
#print(shortCuts)

nowDis = 0
idx= 0
movedDistance[0] = 0

while nowDis < d:
    while idx < n:
        if shortCuts[idx][0] != nowDis:
            break
        if shortCuts[idx][1] <= d:
            shortCutDis = movedDistance[nowDis] + shortCuts[idx][2]
            if shortCutDis < movedDistance[shortCuts[idx][1]]:
                movedDistance[shortCuts[idx][1]] = shortCutDis
        idx += 1
    if movedDistance[nowDis] + 1 < movedDistance[nowDis + 1]:
        movedDistance[nowDis+1] = movedDistance[nowDis]+1
    nowDis += 1
#print(movedDistance)
#print(d)
print(movedDistance[d])