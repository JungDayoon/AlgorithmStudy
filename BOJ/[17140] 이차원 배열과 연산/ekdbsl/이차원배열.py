def rOperation(standard, change):
    newChange = 0
    for i in range(standard):
        numberCnt = [[0,101] for _ in range(101)]
        for j in range(change):
            if Map[i][j] == 0:
                continue
            numberCnt[Map[i][j]][0] = Map[i][j]
            if numberCnt[Map[i][j]][1] == 101:
                numberCnt[Map[i][j]][1] = 1
            else:
                numberCnt[Map[i][j]][1] += 1
            Map[i][j] = 0

        numberCnt = sorted(numberCnt, key=lambda t:(t[1], t[0]))
        idx = 0
        for n in numberCnt:
            if n[1] == 101:
                break
            Map[i][idx] = n[0]
            Map[i][idx+1] = n[1]
            idx += 2
        newChange = max(newChange, idx)

    return newChange

def cOperation(standard, change):
    newChange = 0
    for j in range(standard):
        numberCnt = [[0,101] for _ in range(101)]
        for i in range(change):
            if Map[i][j] == 0:
                continue
            numberCnt[Map[i][j]][0] = Map[i][j]
            if numberCnt[Map[i][j]][1] == 101:
                numberCnt[Map[i][j]][1] = 1
            else:
                numberCnt[Map[i][j]][1] += 1
            Map[i][j] = 0

        numberCnt = sorted(numberCnt, key=lambda t:(t[1], t[0]))
        idx = 0
        for n in numberCnt:
            if n[1] == 101:
                break
            Map[idx][j] = n[0]
            Map[idx+1][j] = n[1]
            idx += 2
        newChange = max(newChange, idx)

    return newChange


r, c, k = map(int, input().split())
r -= 1
c -= 1
maxR = 3
maxC = 3
tmpMap = [[int(x) for x in input().split()] for _ in range(3)]
Map = [[0 for _ in range(100)] for _ in range(100)]
# numberCnt = [[0,0] for _ in range(101)]

for i in range(maxR):
    for j in range(maxC):
        Map[i][j] = tmpMap[i][j]

time = 0
while time < 101:
    if Map[r][c] == k:
        break

    time += 1
    if maxR >= maxC:
        maxC = rOperation(maxR, maxC)
    else:
        maxR = cOperation(maxC, maxR)



print(time if time <= 100 else -1)
