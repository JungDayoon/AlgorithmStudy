import sys
board=[]
space=[]
n=0 #빈칸 개수

def canPut(y,x,num):

    #세로 방향 성립하는지 확인
    for row in range(0,9):
        if(board[row][x]==num):
            return False
    #가로 방향 성립하는지 확인
    for col in range(0,9): 
        if(board[y][col]==num):
            return False

    #3x3 방향 성립하는지 확인
    sy=(int)(y/3)*3 
    sx=(int)(x/3)*3
    for row in range(sy,sy+3):
        for col in range(sx, sx+3):
            if(board[row][col]==num):
                return False

    return True

def sudoku(picked):

    if(picked==n):
        printBoard()
        exit(0)
    
    loc=space[picked]

    for num in range(1,10):
        if(board[loc[0]][loc[1]]== 0):
            if(canPut(loc[0],loc[1],num)==True):
                board[loc[0]][loc[1]]=num
                sudoku(picked+1)
                board[loc[0]][loc[1]]=0


def printBoard():
    for i in range(9):
        for j in range(9):
            print(board[i][j], end= ' ')
        print(' ')


board = [list(map(int,sys.stdin.readline().rsplit())) for _ in range(9)]

for i in range(9):
    for j in range(9):
        if(board[i][j]==0):
            n+=1
            space.append([i,j])

sudoku(0)
