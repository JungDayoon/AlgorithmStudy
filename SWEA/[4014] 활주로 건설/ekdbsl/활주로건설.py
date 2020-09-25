import math

def check(num):
    nowMap = Map[num]
    prev = -1
    cnt = 0
    flag = 0

    for j in range(N):
        if prev == -1:
            prev = nowMap[j]
            cnt += 1
            continue
        
        if nowMap[j] == prev:
            cnt += 1
            if flag == 1 and cnt >= X:
                flag = 0
                cnt = 0
            
        
        elif(abs(nowMap[j] - prev) == 1):
            if flag == 1 and cnt < X:
                return False
            
            if nowMap[j] - prev == 1: #위로 올라가는 경사
                if cnt < X:
                    return False
                cnt = 1

            else: #아래로 내려가는 경사
                flag = 1
                cnt = 1

        else:
            return False
        
        prev = nowMap[j]
    
    if flag == 1:
        return False
    return True
        
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N, X= map(int, input().split())
    Map = [[]for y in range(2*N)]
    for i in range(N):
        Map[i] = [int(x) for x in input().split()]
    for i in range(N):
        for j in range(N):
            Map[N+i].append(Map[j][i])

    # print(numpy.array(Map))

    possibleCnt = 0
    for i in range(2*N):
        if check(i):
            possibleCnt += 1
    
    print("#{} {}".format(test_case, possibleCnt))