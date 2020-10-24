import sys

def play():
    global answer
    score = 0
    start = 0

    for i in Game:
        out = 0
        next = False
        b1, b2, b3 = 0, 0, 0
        while 1:
            for j in range(start, 9):
                #player = Game[i][order[j]]
                if i[order[j]] == 0:
                    out += 1
                elif i[order[j]] == 1:
                    score += b3
                    b1, b2, b3 = 1, b1, b2
                elif i[order[j]] == 2:
                    score += b3 + b2
                    b1, b2, b3 = 0, 1, b1
                elif i[order[j]] == 3:
                    score += b3+b2+b1
                    b1, b2, b3 = 0, 0, 1
                else:
                    score += b1+b2+b3+1
                    b1, b2, b3 = 0, 0, 0
                if out == 3:
                    start = j + 1
                    if start == 9:
                        start = 0
                    next = True
                    break
            if next == True:
                break
            start = 0
    answer = max(answer, score)


def dfs(cnt):
    if cnt == 9:
        play()
        return
    for i in range(9):
        if selected[i] == True:
            continue
        selected[i] = True
        order[i] = cnt
        dfs(cnt+1)
        selected[i] = False


n = int(input())
Game = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]  # Game[1][2] = 3, 2이닝에서 3번째 선수는 3루타를 칩니다.
order = [0 for _ in range(9)]
selected = [False for _ in range(9)]
answer = 0

selected[3] = True
order[3] = 0
dfs(1)

print(answer)
