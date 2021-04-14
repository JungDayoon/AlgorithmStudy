import itertools
import copy
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
def rotate(starty, startx, time, temp):
    before_num = temp[starty][startx]
    y, x = starty, startx
    for i in range(4):
        for k in range(time):
            nexty, nextx = y+dy[i], x+dx[i]
            next_num = temp[nexty][nextx]
            temp[nexty][nextx] = before_num
            before_num = next_num
            y, x = nexty, nextx
    return
def start(choose_order, temp):
    for i in choose_order:
        r, c, s = info[i]
        for j in range(1, s+1):
            rotate(r-j, c-j, j*2, temp)
    min_sum = sum(temp[0])
    for i in range(1, N):
        min_sum = min(min_sum, sum(temp[i]))
    return min_sum

if __name__ == "__main__":
    N, M, K = map(int, input().split())
    arr = []
    for i in range(N):
        arr.append(list(map(int, input().split())))
    info = []
    order = []
    for i in range(K):
        r, c, s = map(int, input().split())
        info.append([r-1, c-1, s])
        order.append(i)

    orderlist = list(itertools.permutations(order, K)) #순서 정하기 
    answer = float('inf')
    for i in orderlist:
        temp = copy.deepcopy(arr)
        answer = min(answer, start(i, temp))
    print(answer)
    