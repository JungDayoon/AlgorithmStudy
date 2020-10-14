def isIn(x, y):
    if minC <= x <= maxC and minR <= y <= maxR:
        return True
    return False


T = int(input())
maxR = 2000
minR = -2000
maxC = 2000
minC = -2000
pos = [[0,1], [0,-1], [-1,0], [1,0]] # x, y

for t_c in range(1, T+1):
    N = int(input())
    atomList = [[int(x) for x in input().split()] for _ in range(N)]
    atomList = list(map(lambda t: [t[0]*2, t[1]*2, t[2], t[3]], atomList))
    energy = 0

    while len(atomList) > 0:
        removeList = []
        for aItem in atomList:
            aItem[0] += pos[aItem[2]][0]
            aItem[1] += pos[aItem[2]][1]

            if not isIn(aItem[0], aItem[1]):
                removeList.append([aItem[0], aItem[1], aItem[2], aItem[3]])

        while removeList:
            atomList.pop(atomList.index(removeList.pop()))

        if len(atomList) == 0:
            break

        atomList = sorted(atomList, key=lambda t: (t[0], t[1]))
        prev = atomList[0]
        energy += prev[3]
        removeList.append(prev)
        flag = False
        for aIdx in range(1, len(atomList)):
            if prev[0] == atomList[aIdx][0] and prev[1] == atomList[aIdx][1]:
                flag = True
            else:
                if not flag:
                    energy -= prev[3]
                    removeList.pop()
                flag = False

            energy += atomList[aIdx][3]
            removeList.append(atomList[aIdx])
            prev = atomList[aIdx]

        if not flag:
            removeList.pop()
            energy -= atomList[len(atomList) - 1][3]

        if len(removeList) > 1:
            while removeList:
                atomList.pop(atomList.index(removeList.pop()))

    print("#{} {}".format(t_c, energy))
