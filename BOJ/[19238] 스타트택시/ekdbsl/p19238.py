from collections import deque

N, M, fuel = map(int, input().split())
Map = [[int(x) for x in input().split()]for y in range(N)]
taxi = [int(x)-1 for x in input().split()]
passenger = [[int(x)-1 for x in input().split()]for y in range(M)]

pos = [[1,0], [-1,0], [0,1], [0,-1]]
Max = 100000000

def isIn(y, x):
    if y >= 0 and y<N and x >=0 and x<N:
        return True
    return False

def findDistance(taxi_y, taxi_x, passengerMap):
    tmpMap = [[0 for x in range(N)]for y in range(N)]
    queue = deque()
    queue.append([taxi_y, taxi_x])
    tmpMap[taxi_y][taxi_x] = 1

    result = []
    while queue:
        curr = queue.popleft()
        if passengerMap[curr[0]][curr[1]] >= 0:
            result.append([passengerMap[curr[0]][curr[1]], tmpMap[curr[0]][curr[1]] - 1])

        for i in range(4):
            ny = curr[0] + pos[i][0]
            nx = curr[1] + pos[i][1]
            
            if isIn(ny, nx) and Map[ny][nx] == 0 and tmpMap[ny][nx] == 0:
                queue.append([ny, nx])
                tmpMap[ny][nx] = tmpMap[curr[0]][curr[1]] + 1
                
    
    if len(result) > 0:
        return result
    return -1

def moveTaxi(start_y, start_x, end_y, end_x):
    tmpMap = [[0 for x in range(N)]for y in range(N)]
    queue = deque()
    queue.append([start_y, start_x])
    tmpMap[start_y][start_x] = 1

    while queue:
        curr = queue.popleft()

        if curr[0] == end_y and curr[1] == end_x:
            return tmpMap[end_y][end_x]-1
        
        for i in range(4):
            ny = curr[0] + pos[i][0]
            nx = curr[1] + pos[i][1]

            if isIn(ny, nx) and Map[ny][nx] == 0 and tmpMap[ny][nx] == 0:
                queue.append([ny, nx])
                tmpMap[ny][nx] = tmpMap[curr[0]][curr[1]] + 1
    
    return Max

def solution(taxi):
    global fuel

    while passenger:
        passengerMap = [[-1 for _ in range(N)]for y in range(N)]
        for i in range(len(passenger)):
            passengerMap[passenger[i][0]][passenger[i][1]] = i

        minD = findDistance(taxi[0], taxi[1], passengerMap)
        if minD == -1:
            return -1
        
        choice = []
        for m in minD:
            choice.append([m[1], passenger[m[0]][0], passenger[m[0]][1], passenger[m[0]][2], passenger[m[0]][3], m[0]])
        newD = sorted(choice, key = lambda x: [x[0], x[1], x[2]])

        select = newD[0]
        passenger.pop(select[5])
        distanceToDest = moveTaxi(select[1], select[2], select[3], select[4])
        waste = fuel - (select[0] + distanceToDest)
        if waste >= 0:
            taxi = [select[3], select[4]]
            fuel = waste + 2*distanceToDest
        else:
            return -1

    return fuel

print(solution(taxi))