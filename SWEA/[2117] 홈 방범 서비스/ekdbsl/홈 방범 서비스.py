T = int(input())

maxHouse = 0

def Cost(K):
    return K*K + (K-1)*(K-1)

def makeRhombus(serviceArea, K):
    y = 0
    x = K
    R = 0
    for y in range(2*K+1):
        serviceArea[y][x] = 1
        for r in range(1, R+1):
            serviceArea[y][x-r] = 1
            serviceArea[y][x+r] = 1
        
        if y < K:
            R += 1
        else:
            R -= 1

def MapToNewMap(newMap, Map, K):
    for i in range(len(Map)):
        for j in range(len(Map[i])):
            newMap[i+K][j+K] = Map[i][j]

def findCost(newMap, serviceArea, serviceLen):
    global maxHouse
    house = 0

    for i in range(len(newMap)-serviceLen+1):
        for j in range(len(newMap)-serviceLen+1):
            y = i
            x = j

            #서비스를 받는 집 수
            count = 0
            for y in range(serviceLen):
                for x in range(serviceLen):
                    if newMap[y+i][x+j] and serviceArea[y][x] :
                        count += 1
            
            if count > 0:
                profit = count * M - Cost(K)
                if profit >= 0:
                    maxHouse = max(maxHouse, count)
                    house = max(house, count)

    return house

for test_case in range(1, T + 1):
    maxHouse = 0
    N, M = map(int, input().split())
    Map = [[int(x) for x in input().split()]for y in range(N)]

    K = 1
    while True:
        serviceLen = 2*K-1
        serviceArea = [[0 for _ in range(serviceLen)]for _ in range(serviceLen)]
        makeRhombus(serviceArea, K-1)
        newMap = [[0 for _ in range(N + 2*(serviceLen-1))]for _ in range(N + 2*(serviceLen-1))]
        MapToNewMap(newMap, Map, serviceLen-1)

        if findCost(newMap, serviceArea, serviceLen) == 0 and (K*2-1) > N:
            break
        
        K +=1
    print("#{} {}".format(test_case, maxHouse))