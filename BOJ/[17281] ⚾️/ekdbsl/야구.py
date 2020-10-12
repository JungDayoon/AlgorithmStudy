from itertools import permutations


def makePermutation(now, goal, item):
    global playerCandidate
    global visited

    if now == goal:
        playerCandidate.append(item[:])
        return

    for i in range(len(playerNum)):
        if not visited[i]:
            item[now] = playerNum[i]
            visited[i] = True
            makePermutation(now + 1, goal, item)
            visited[i] = False


N = int(input())  # 주자번호 0~8번까지
inningScore = [[int(x) for x in input().split()] for _ in range(N)]
playerNum = [int(x) + 1 for x in range(8)]

# playerCandidate = list(permutations(playerNum, 8))
visited = [False for _ in range(8)]
makePermutation(0, 8, [0, 0, 0, 0, 0, 0, 0, 0])

maxScore = 0

for pc in playerCandidate:
    pc = list(pc)
    pc.insert(3, 0)
    # print(pc)

    score = 0
    outCnt = 0
    Base = [0, 0, 0, 0]
    player = 0
    for inning in range(N):
        while outCnt < 3:
            hitType = inningScore[inning][pc[player]]
            if hitType == 0:  # 아웃
                outCnt += 1
            elif hitType == 4:  # 홈런
                Base[0] = 1  # 타자
                score += Base.count(1)
                Base = [0 for _ in range(4)]  # 주자 모두 초기화
            else:
                Base[0] = 1  # 타자
                for i in range(len(Base) - 1, -1, -1):
                    if Base[i] == 1:  # 주자 존재
                        now = i + hitType
                        if now > 3:  # 홈으로 들어옴
                            score += 1
                            Base[i] = 0  # 원래 있던 자리는 0으로
                        else:
                            # 주자 이동
                            Base[now] = 1
                            Base[i] = 0  # 원래 있던 자리는 0으로
            player = (player + 1) % 9
        #  이닝 종료
        outCnt = 0
        Base = [0 for _ in range(4)]
    maxScore = max(score, maxScore)

print(maxScore)
