import sys
def promising(line):
    flag = [[0]*10 for _ in range(3)]
    for i in range(0, len(line)):
        for j in line[i]:
            if(j!=0):
                flag[i][j]+=1

    number = []

    for i in range(1, 10):
        if(flag[0][i]==0 and flag[1][i]==0 and flag [2][i]==0):
            number.append(i)
        # elif(flag[0][i]>1):
        #     return [-1]
    return number
    

def make_sudoku(check_sudoku, zero_num):
    if(zero_num==0):
        for i in check_sudoku:
            for j in i:
                print(j, end = " ")
            print()
        #print( check_sudoku)
        sys.exit()
        return 1
    for i in range(0, 9):
        for j in range(0, 9):
            if(check_sudoku[i][j]==0):
                line1 = []
                line1.append(check_sudoku[i])
                line2 = []
                for k in check_sudoku:
                    line2.append(k[j])
                line1.append(line2)
                line3 = []
                x = ((i+3)//3-1)*3
                y = ((j+3)//3-1)*3
                for k in range(x, x+3):
                    for p in range(y, y+3):
                        line3.append(check_sudoku[k][p])
                line1.append(line3)
                number = promising(line1)
                if(len(number)==0):
                    return
                for k in number:
                    check_sudoku[i][j]=k
                    make_sudoku(check_sudoku, zero_num-1)
                    #print("ing"+str(i))
                    check_sudoku[i][j]=0
                return

zero_num=0
sudoku = []
for i in range(9):
    new_sudoku = list(map(int, input().split()))
    zero_num+=new_sudoku.count(0)
    sudoku.append(new_sudoku)
make_sudoku(sudoku, zero_num)


