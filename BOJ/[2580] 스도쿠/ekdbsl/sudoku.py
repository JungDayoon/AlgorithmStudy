N = 9
sudokuMap = [[int(x) for x in input().split()]for y in range(N)]
number = [int(x) for x in range(N)]
start = list(map(lambda x: (x //3)*3, number))
chance = list(map(lambda x: x+1, number))
flag = False

def satisfyCondition(sudokuMap, nowItem):
    now_x = nowItem[1]
    now_y = nowItem[0]

    initial = sudokuMap[now_y][now_x]

    for i in range(0, N):
        if i != now_x and initial == sudokuMap[now_y][i]:
            return False
        if i != now_y and initial == sudokuMap[i][now_x]:
            return False
    
    for i in range(start[now_y], start[now_y]+3):
        for j in range(start[now_x], start[now_x] +3):
            if i != now_y and j != now_x and initial == sudokuMap[i][j]:
                return False
    
    return True

def solution(listidx, now, goal):
    global sudokuMap
    global zeroList
    global flag

    if now == goal:
        flag = True
        return
    
    nowItem = zeroList[listidx]
    for c in chance:
        sudokuMap[nowItem[0]][nowItem[1]] = c
        if satisfyCondition(sudokuMap, nowItem):
            solution(listidx+1, now+1, goal)
        if flag:
            return
        sudokuMap[nowItem[0]][nowItem[1]] = 0

zeroList = []
for i in range(N):
    for j in range(N):
        if sudokuMap[i][j] == 0:
            zeroList.append([i, j])

solution(0, 0, len(zeroList))

for i in range(N):
    out = ""
    for j in range(N):
        out += str(sudokuMap[i][j]) + " "
    print(out)