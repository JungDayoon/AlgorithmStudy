puyo = [list(input()) for _ in range(12)]
answer = 0
D = [[-1,0],[1,0],[0,-1],[0,1]] #상하좌우

def find_pop(i,j,depth,check,pop_list):
    for d in D:
        nextI,nextJ = i+d[0],j+d[1]
        if 0<=nextI<12 and 0<=nextJ<6 and puyo[nextI][nextJ]==puyo[i][j] and check[nextI][nextJ]==0:
            pop_list.append([nextI,nextJ])
            check[nextI][nextJ]=1
            find_pop(nextI,nextJ,depth+1,check,pop_list)
    return pop_list

flag = True
while flag:
    flag = False
    check = [[0]*6 for _ in range(12)]
    pop = [[False]*6 for _ in range(12)]

    # 4개 이상인거 찾기
    for i in range(12):
        for j in range(6):
            check[i][j]=1
            if puyo[i][j]==".":
                continue
            pop_list = find_pop(i,j,1,check,[[i,j]])
            if len(pop_list)>=4:
                for p in pop_list:
                    pop[p[0]][p[1]]=True
                flag = True

    # 재배열해주기
    if flag:
        for i in range(12):
            for j in range(6):
                if pop[i][j]:
                    for tempI in range(i,0,-1):
                        puyo[tempI][j] = puyo[tempI-1][j]
                    puyo[0][j]="."
        answer+=1
    else:
        break

print(answer)