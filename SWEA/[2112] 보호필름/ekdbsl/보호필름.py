from itertools import combinations
flag = False

def changeMap(row, type):
    for c in range(W):
        filmMap[row][c] = type

def saveRow(orig, new):
    for c in range(W):
        new[c] = orig[c]

def passInspection():
    for i in range(W):
        maxCount = 0
        count = 1
        prev = filmMap[0][i]
        for j in range(1, D):
            if(filmMap[j][i] == prev):
                count += 1
            else:
                maxCount = max(maxCount, count)
                count = 1
            prev = filmMap[j][i]
        maxCount = max(maxCount, count)
        if maxCount < K:
            return False
    
    return True
    
def drugBackTracking(now, goal, comb):
    global flag
    
    if flag == True:
        return

    if now == goal:
        if passInspection():
            flag = True
        return
    
    tmp = [0 for _ in range(W)]
    saveRow(filmMap[comb[now]], tmp)
    
    changeMap(comb[now], 0) # A성분 투입
    drugBackTracking(now+1, goal, comb)

    changeMap(comb[now], 1) # B성분 투입
    drugBackTracking(now+1, goal, comb)

    saveRow(tmp, filmMap[comb[now]])


T = int(input())
for test_case in range(1, T + 1):
    D, W, K = map(int, input().split()) #두께, 가로크기, 합격기준
    filmMap = [[int(x) for x in input().split()]for y in range(D)]
    drugInput = [int(x) for x in range(D)]
    flag = False

    for drugCnt in range(D+1):
        drugComb = list(combinations(drugInput, drugCnt))
        
        # print(drugComb)
        
        for dc in drugComb:
            drugBackTracking(0, drugCnt, dc)
            if flag == True:
                break
        if flag == True:
            break
    
    print("#{} {}".format(test_case, drugCnt))
