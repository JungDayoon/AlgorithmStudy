visited = [False]*25
stu_map = [[False]*5 for i in range(5)]
temp = [[False]*5 for i in range(5)]
total_cnt = 0
check_cnt = 0
dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]
def isin(x, y):
    if(0<=x<5 and 0<=y<5):
        return True
    return False
def check(y, x):
    #using DFS
    temp[y][x] = True
    global check_cnt
    check_cnt+=1

    for i in range(4):
        nextx = x + dx[i]
        nexty = y + dy[i]
        if(isin(nextx, nexty) and stu_map[nexty][nextx] and temp[nexty][nextx]==False):
            check(nexty, nextx)



def backtracking(cnt, now_dir, Scnt, Ycnt):
    if(Ycnt > 3):
        return
    if(cnt == 7):
        global temp
        global check_cnt
        check_cnt = 0
        flag = False
        temp = [[False]*5 for i in range(5)]
        for i in range(5):
            for j in range(5):
                if(stu_map[i][j] == True):
                    check(i, j)
                    if(check_cnt == 7):
                        global total_cnt
                        total_cnt+=1
                    flag = True
                    break
            if(flag == True):
                break
        return
    for i in range(now_dir,25):
        if(visited[i]==False):
            visited[i] = True
            stu_map[i//5][i%5] = True
            if(student[i//5][i%5] == 'Y'):
                backtracking(cnt+1, i, Scnt, Ycnt+1)
            else:
                backtracking(cnt+1, i, Scnt+1, Ycnt)

            stu_map[i//5][i%5] = False
            visited[i] = False
    

    




if __name__ == "__main__":
    student = []

    for i in range(5):
        student.append(list(input()))
    backtracking(0, 0, 0, 0)
    print(total_cnt)
    

