from itertools import combinations

def solution(people1, people2):
    global person
    rS = [[], []]
    distance = [[] for _ in range(len(person))]

    for i in range(len(people1)):
        rS[0].append(abs(people1[i][1] - stair[0][1]) + abs(people1[i][2] - stair[0][2]))

    for i in range(len(people2)):
        rS[1].append(abs(people2[i][1] - stair[1][1]) + abs(people2[i][2] - stair[1][2]))

    rS[0].sort()
    rS[1].sort()

    # print(distance)
    stack = [[], []]
    remain = [len(rS[0]) % 3, len(rS[1]) % 3]
    lasttime = [[], []]
    for a in range(2):
          if remain[a] == 0:
                remain[a] = 2
          else:
                remain[a] = remain[a] - 1

          # 3의 배수 저장
          for n in range(remain[a], len(rS[a]), 3):
                stack[a].append(int(rS[a][n]) + 1)

          lasttime[a] = 0
          for j in range(len(stack[a])):
                if j == len(stack[a]) - 1:
                      lasttime[a] = lasttime[a] + stack[a][j] + stair[a][0]
                      break
                t = lasttime[a] + stack[a][j] + stair[a][0] - stack[a][j + 1]
                if t > 0:
                      lasttime[a] = t
                else:
                      lasttime[a] = 0

    result = 0
    result = lasttime[0] if lasttime[0] > lasttime[1] else lasttime[1]

    return result


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N = int(input())
    Map = [[int(x) for x in input().split()] for y in range(N)]
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

    for i in range(idx // 2 + 1):
        comb = list(combinations(person, i))
        for comb_item in comb:
            other = [x for x in person if x not in comb_item]
            minTime.append(solution(comb_item, other))
            minTime.append(solution(other, comb_item))

    # print(minTime)
    print("#{} {}".format(test_case, min(minTime)))