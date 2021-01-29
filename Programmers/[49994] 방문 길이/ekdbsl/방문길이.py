def isIn(y, x):
    if 0 <= y <= 10 and 0 <= x <= 10:
        return True
    return False


def solution(dirs):
    nx = 5
    ny = 5
    path = set()

    for d in dirs:
        if d == "U" and isIn(ny - 1, nx):
            path.add(((ny, nx), (ny-1, nx)))
            path.add(((ny-1, nx), (ny, nx)))
            ny -= 1
        elif d == "D" and isIn(ny + 1, nx):
            path.add(((ny, nx), (ny + 1, nx)))
            path.add(((ny + 1, nx), (ny, nx)))
            ny += 1
        elif d == "L" and isIn(ny, nx - 1):
            path.add(((ny, nx), (ny, nx - 1)))
            path.add(((ny, nx - 1), (ny, nx)))
            nx -= 1
        elif d == "R" and isIn(ny, nx + 1):
            path.add(((ny, nx), (ny, nx + 1)))
            path.add(((ny, nx + 1), (ny, nx)))
            nx += 1

    return len(list(path)) // 2


print(solution("ULURRDLLU"))
print(solution("LULLLLLLU"))
