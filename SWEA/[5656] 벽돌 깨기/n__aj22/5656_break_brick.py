import sys


T = int(input())
dx = [0,1,0,-1]
dy = [-1, 0, 1,0]
brick_num = 9999
N, W, H = 0, 0, 0
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.

def remove_blank(arr):
    new_arr = [[0]*W for _ in range(H)]
    
    for i in range(W):
        temp = []
        for j in range(H):
            if(arr[j][i] != 0):
                temp.append(arr[j][i])
        
        for j in range(len(temp)):
            new_arr[H-len(temp)+j][i] = temp[j]
    
    return new_arr
def isIn(x, y):
    if(0<=x<W and 0<=y<H):
        return True
    return False

def remove(x, y, arr):
    new_arr = [[False]*W for _ in range(H)]
    queue = []
    queue.append([x, y])
    
    while(queue):
        this_x, this_y = queue.pop()
        new_arr[this_y][this_x] = True
        for i in range(1, arr[this_y][this_x]):
            for j in range(4):
                next_x = this_x+dx[j]*i
                next_y = this_y+dy[j]*i
                if(isIn(next_x, next_y) and arr[next_y][next_x]!=0 and new_arr[next_y][next_x]==False):
                    queue.append([next_x, next_y])

    for i in range(H):
        for j in range(W):
            if(new_arr[i][j]):
                arr[i][j] = 0
    
    return(remove_blank(arr))
def count_brick_num(arr):
    num = 0
    for i in range(H):
        for j in range(W):
            if(arr[i][j]!=0):
                num+=1
    return num

def copyarr(arr):
    temp = []
    for i in arr:
        new_temp = []
        for j in i:
            new_temp.append(j)
        temp.append(new_temp)
    return temp
def select_brick(arr, num, choose_num):
    if(num == choose_num or count_brick_num(arr) == 0):
        global brick_num
        brick_num = min(count_brick_num(arr), brick_num)
        return
    
    for i in range(W):
        for j in range(H):
            if(arr[j][i]!=0):
                temp = copyarr(arr)
                select_brick(remove(i, j, arr), num+1, choose_num)
                arr = copyarr(temp)
                break

for test_case in range(1, T + 1):
    N, W, H = map(int, input().split())
    brick_num = 9999
    arr = []
    for i in range(H):
        arr.append(list(map(int, input().split())))

    select_brick(arr, 0, N)
    print("#"+str(test_case)+" "+str(brick_num))
