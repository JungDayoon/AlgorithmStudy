class Dir:
    def __init__(self, y, x):
        self.y = y
        self.x = x


def isIn(y, x):
    if 0 <= y <= 10 and 0 <= x <= 10:
        return True
    return False


def solution(dirs):
    answer = 0
    Map = [[{"U": False, "D": False, "L": False, "R": False} for _ in range(11)] for _ in range(11)]
    now = Dir(5, 5)
    Map[now.y][now.x] = True

    for d in dirs:
        if d == "U" and isIn(now.y - 1, now.x):
            now.y -= 1
            if not Map[now.y][now.x][d]:
                Map[now.y][now.x][d] = True
                answer += 1
        elif d == "D" and isIn(now.y + 1, now.x):
            now.y += 1
            if not Map[now.y][now.x][d]:
                Map[now.y][now.x][d] = True
                answer += 1
        elif d == "L" and isIn(now.y, now.x - 1):
            now.x -= 1
            if not Map[now.y][now.x][d]:
                Map[now.y][now.x][d] = True
                answer += 1
        elif d == "R" and isIn(now.y, now.x + 1):
            now.x += 1
            if not Map[now.y][now.x][d]:
                Map[now.y][now.x][d] = True
                answer += 1


    # for i in Map:
    #     print(' '.join(map(str, i)))

    return answer


print(solution("ULURRDLLU"))
print(solution("LULLLLLLU"))