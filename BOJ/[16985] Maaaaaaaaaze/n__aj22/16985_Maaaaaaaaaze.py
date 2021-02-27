from itertools import product
from itertools import permutations
import collections
answer = float('inf')
dx = [0, 0, 1, 0, 0, -1]
dy = [0, -1, 0, 0, 1, 0]
dz = [-1, 0, 0, 1, 0, 0]
def isin(x, y, z):
    if(0<=x<5 and 0<=y<5 and 0<=z<5):
        return True
    return False
def escape_maze(arr):
    #start = 0, 0, 0
    #end = 4, 4, 4
    visited = [[[False]*5 for _ in range(5)] for _ in range(5)]
    queue = collections.deque()
    queue.append([0, 0, 0, 0])#x, y, z, count
    visited[0][0][0] = True
    while(queue):
        x, y, z, count = queue.popleft()
        for i in range(6):
            nextx = x + dx[i]
            nexty = y + dy[i]
            nextz = z + dz[i]
            if(isin(nextx, nexty, nextz)):
                if(arr[nextx][nexty][nextz] == 1 and visited[nextx][nexty][nextz]==False):
                    if([nextx, nexty, nextz] == [4, 4, 4]):
                        return count+1
                    queue.append([nextx, nexty, nextz, count+1])
                    visited[nextx][nexty][nextz] = True
    return -1

def rotate(arr, N):
    arr2 = arr
    for n in range(N):
        arr2 = []
        for i in range(5):
            new_arr = []
            for j in range(4, -1, -1):
                new_arr.append(arr[j][i])
            arr2.append(new_arr)
        arr = arr2
    return arr2

if __name__ == "__main__":
    option = [0, 1, 2, 3]
    board = [0, 1, 2, 3, 4]
    option_list = list(product(option, repeat=5)) #5개 보드의 회전 상태 4^5
    board_list = list(permutations(board, 5)) #5개 보드의 순서 
    
    base_board = []
    for i in range(5):
        new_board = []
        for j in range(5):
            new_board.append(list(map(int, input().split())))
        base_board.append(new_board)
    find_min = False
    for i in board_list:
        for j in option_list:
            check_board = []
            for k in range(5):
                check_board.append(rotate(base_board[i[k]], j[k]))
            if(check_board[0][0][0] == 1 and check_board[4][4][4] == 1):
                flag = escape_maze(check_board)
                if(flag != -1):
                    answer = min(answer, flag)
                if(answer == 12):
                    find_min = True
                    break
        if(find_min == True):
            break
        
    if(answer == float('inf')):
        print(-1)
    else:
        print(answer)
