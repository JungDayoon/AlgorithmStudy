from itertools import combinations

def calculTime(distance):
    global stair
    time = 0
    finish = 0
    stairLine = [[]for _ in range(2)]
    
    while True:
        if finish == len(distance):
            break
        time += 1
        for i in range(2):
            for j in range(len(stairLine[i])):
                if j >= 3:
                    break
                stairLine[i][j] -= 1
            
            while len(stairLine[i]) > 0 and stairLine[i][0] == 0:
                finish += 1
                stairLine[i].pop(0)


        for i in range(len(distance)):
            if distance[i][0] == 0:
                stairNum = distance[i][1]
                stairLine[stairNum].append(stair[stairNum][0])
            distance[i][0] -= 1
    
    return time


def solution(people1, people2):
    global person
    distance = [[] for _ in range(len(person))]

    for i in range(len(people1)):
        distance[people1[i][0]] = [abs(people1[i][1] - stair[0][1]) + abs(people1[i][2] - stair[0][2]), 0] # 계단까지의 거리, 계단 번호
    
    for i in range(len(people2)):
        distance[people2[i][0]] = [abs(people2[i][1] - stair[1][1]) + abs(people2[i][2] - stair[1][2]), 1]
    
    # print(distance)
    return calculTime(distance)

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N = int(input())
    Map = [[int(x) for x in input().split()]for y in range(N)]
    person = []
    stair = []
    idx = 0
    minTime = []

    for i in range(N):
        for j in range(N):
            if Map[i][j] == 1:
                person.append([idx, i, j])
                idx += 1
            elif Map[i][j] >= 2:
                stair.append([Map[i][j], i, j])

    for i in range(N//2+1):
        comb = list(combinations(person, i))
        # print(comb)
        for comb_item in comb:
            other = [x for x in person if x not in comb_item]
            minTime.append(solution(comb_item, other))
            minTime.append(solution(other, comb_item))
    
    # print(minTime)
    print("#{} {}".format(test_case, min(minTime)))