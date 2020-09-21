import sys

pos = [[0,0],[-1,0],[0,1],[1,0],[0,-1]]

def isIn(x, y):
    if(x > 0 and x<=10 and y>0 and y<=10):
        return True
    return False

def drawMap(chargeNum, charger):
    x = charger[0]
    y = charger[1]
    chargeRange = charger[2]
    power = charger[3]
    charger_map[y][x].append([chargeNum, power])

    for i in range(1, 11):
        for j in range(1, 11):
            if(abs(x-j)+abs(y-i) <= chargeRange):
                if [chargeNum, power] not in charger_map[i][j]:
                    charger_map[i][j].append([chargeNum, power])

def findSum(sumCharge,personA_loc_x, personA_loc_y, personB_loc_x, personB_loc_y):
    maxCharge = 0
    tmpCharge = []
    
    if len(charger_map[personA_loc_y][personA_loc_x]) == 0 and len(charger_map[personB_loc_y][personB_loc_x]) == 0:
        sumCharge += 0

    elif len(charger_map[personA_loc_y][personA_loc_x]) == 0:
        for k in range(len(charger_map[personB_loc_y][personB_loc_x])):
            chargerB = charger_map[personB_loc_y][personB_loc_x][k]
            tmpCharge.append(chargerB[1])
        maxCharge = max(tmpCharge)
        sumCharge += maxCharge
        
    elif len(charger_map[personB_loc_y][personB_loc_x]) == 0:
        for j in range(len(charger_map[personA_loc_y][personA_loc_x])):
            chargerA = charger_map[personA_loc_y][personA_loc_x][j]
            tmpCharge.append(chargerA[1])
        maxCharge = max(tmpCharge)
        sumCharge += maxCharge

    else:
        for j in range(len(charger_map[personA_loc_y][personA_loc_x])):
            for k in range(len(charger_map[personB_loc_y][personB_loc_x])):
                chargerA = charger_map[personA_loc_y][personA_loc_x][j]
                chargerB = charger_map[personB_loc_y][personB_loc_x][k]

                if(chargerA[0] == chargerB[0]): #use same BC
                    tmpCharge.append(chargerA[1])
                else:
                    tmpCharge.append(chargerA[1] + chargerB[1])
        
        maxCharge = max(tmpCharge)
        sumCharge += maxCharge
    
    return sumCharge

T = int(input())
charger_map = [[[] for x in range(11)]for y in range(11)]

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    
    M, A = map(int, input().split())
    charger_map = [[[] for x in range(11)]for y in range(11)]

    personA = [int(x) for x in input().split()]
    personB = [int(x) for x in input().split()]
    personA_loc_x = 1
    personA_loc_y = 1
    personB_loc_x = 10
    personB_loc_y = 10

    for i in range(A):
        tmpCharger = [int(x) for x in input().split()]
        drawMap(i+1, tmpCharger)

    sumCharge = findSum(0, personA_loc_x, personA_loc_y, personB_loc_x, personB_loc_y)

    for i in range(1, 11):
        mapstr = ""
        for j in range(1, 11):
            mapstr += str(len(charger_map[i][j])) + " "
        print(mapstr)

    for i in range(M):
        personA_loc_y += pos[personA[i]][0]
        personA_loc_x += pos[personA[i]][1]
        personB_loc_y += pos[personB[i]][0]
        personB_loc_x += pos[personB[i]][1]

        sumCharge = findSum(sumCharge, personA_loc_x, personA_loc_y, personB_loc_x, personB_loc_y)

    print("#" + str(test_case)+ " "+ str(sumCharge))
