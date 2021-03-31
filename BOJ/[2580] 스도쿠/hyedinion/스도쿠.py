flag = 0


def print_space(space):
    for i in range(9):
        for n in space[i]:
            print(n,end=' ')
        print()


def valid_num_list(space, i, j):
    exist_list = []
    #가로
    for k in range(9):
        exist_list.append(space[i][k])
    #세로
    for k in range(9):
        exist_list.append(space[k][j])
    #네모
    a,b=i%3,j%3
    a = i-a
    b = j-b
    for x in range(3):
        for y in range(3):
            exist_list.append(space[a+x][b+y])

    valid_list = [ i for i in range(1,10) if i not in exist_list ]
    return valid_list


import copy
def sdoku(space, index_list):
    global flag
    #종료조건
    if len(index_list)==0:
        flag = 1
        print_space(space)
    
    if flag == 1:
        return
    l = index_list[:]
    index = l.pop(0)
    i,j = index[0],index[1]

    for k in valid_num_list(space,i,j):
        space[i][j] = k
        sdoku(space,l)
        if flag == 1:
            return

    space[i][j]=0



#초기화
space=[]
for i in range(9):
    space.append( list(map(int,input().split())))
index_list = []
for i in range(9):
    for j,s in enumerate(space[i]):
        if s==0:
            index_list.append((i,j))

#계산
sdoku(space, index_list)
