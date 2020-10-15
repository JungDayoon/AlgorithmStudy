N = int(input())
arr = []
dx = [-1,1]
real_min = 9999
for i in range(N):
    arr.append(list(map(int, input().split())))
def rotate(num):
    if(num == 0):
        return 1
    else:
        return 0
def check_number(five_num, visit, x, y, d1, d2):
    min_num = five_num
    max_num = five_num
    num = 0
    for i in range(0, y):
        for j in range(0, x+d1+1):
            if (visit[i][j] != 5):
                num+=arr[i][j]
    min_num = min(num, min_num)
    max_num = max(num, max_num)

    num = 0
    for i in range(0, y-d1+d2+1):
        for j in range(x+d1+1, N):
            if (visit[i][j] != 5):
                num += arr[i][j]
    min_num = min(num, min_num)
    max_num = max(num, max_num)

    num = 0
    for i in range(y, N):
        for j in range(0, x+d2):
            if (visit[i][j] != 5):
                num += arr[i][j]
    min_num = min(num, min_num)
    max_num = max(num, max_num)

    num = 0
    for i in range(y-d1+d2+1, N):
        for j in range(x+d2, N):
            if (visit[i][j] != 5):
                num += arr[i][j]
    min_num = min(num, min_num)
    max_num = max(num, max_num)
    global real_min
    real_min = min(max_num-min_num, real_min)


def make_five(x, y, d1, d2):
    # [x,y]//x+d1,y-d1 // x+d1+d2, y-d1+d2//x+d2, y+d2
    visited = [[0]*N for _ in range (N)]
    leftstartx = x+d1
    starty = y-d1
    rightstartx = x + d1
    visited[starty][leftstartx] = 5
    five_num = 0
    five_num+=arr[starty][leftstartx]
    dir1 = 0
    dir2 = 1
    while(True):
        leftstartx = leftstartx+dx[dir1]
        rightstartx = rightstartx + dx[dir2]
        starty = starty + 1
        for i in range(leftstartx, rightstartx+1):
            visited[starty][i] = 5
            five_num+=arr[starty][i]
        if (leftstartx == rightstartx):
            break
        if(leftstartx == x):
            dir1 = rotate(dir1)
        if(rightstartx == x+d1+d2):
            dir2 = rotate(dir2)
    check_number(five_num, visited, x, y, d1, d2)
if __name__ == "__main__":
    for i in range(2, N):
        for j in range(1, i):
            d1 = j
            d2 = i-j
            for y in range(d1, N-d2):
                for x in range(0, N-d1-d2):
                    make_five(x, y, d1, d2)
    print(real_min)

