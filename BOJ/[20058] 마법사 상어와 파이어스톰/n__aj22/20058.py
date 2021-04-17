start_index = [[0, 1], [1, 1], [1, 0], [0, 0]]
dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]
def rotate(square, w):
    # print("square")
    # for i in square:
    #     print(i)
    temp = []
    for x in range(w):
        new_temp = []
        for y in range(w-1, -1, -1):
            new_temp.append(square[y][x])
        temp.append(new_temp)
    return temp
def isin(y, x, w):
    if(0<=y<w and 0<=x<w):
        return True
    return False
def melt_ice(w):
    check = [[False]*w for _ in range(w)]
    for i in range(w):
        for j in range(w):
            if(arr[i][j]>0):
                num = 0
                for k in range(4):
                    nexti, nextj = i+dy[k], j+dx[k]
                    if(isin(nexti, nextj, w) and arr[nexti][nextj]>0):
                        num+=1
                if(num<3):
                    check[i][j] = True
    
    for i in range(w):
        for j in range(w):
            if(check[i][j]):
                arr[i][j]-=1
def calculate_ice(y, x, w):
    queue = [[y, x]]
    visited[y][x] = True
    sum_ice = arr[y][x]
    num_ice = 1

    while(queue):
        y, x = queue.pop(0)
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx, w) and arr[nexty][nextx] and not visited[nexty][nextx]):
                num_ice+=1
                sum_ice+=arr[nexty][nextx]
                visited[nexty][nextx] = True
                queue.append([nexty, nextx])

    return sum_ice, num_ice

if __name__ == "__main__":
    N, Q = map(int, input().split())
    width = 2**N
    arr = []

    for i in range(width):
        arr.append(list(map(int, input().split())))
    
    L = list(map(int, input().split()))
    for l in L:
        rotate_width = 2**l
        for starty in range(0, width, rotate_width):
            for startx in range(0, width, rotate_width):
                rotate_temp = []
                for i in range(rotate_width):
                    rotate_temp.append(arr[starty+i][startx:startx+rotate_width])
                after_rotate = rotate(rotate_temp, rotate_width)
                for i in range(rotate_width):
                    for j in range(rotate_width):
                        arr[starty+i][startx+j] = after_rotate[i][j]

        melt_ice(width)

    
    answer = 0
    max_ice = 0
    visited = [[False]*width for _ in range(width)]
    for i in range(width):
        for j in range(width):
            if not visited[i][j] and arr[i][j]:
                sum_ice, num_ice = calculate_ice(i, j, width)
                answer+=sum_ice
                max_ice = max(num_ice, max_ice)

    print(answer)
    print(max_ice)