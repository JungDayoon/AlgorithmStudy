def count_home(i,j,s,home):
    cnt = 0
    #집을 다 돌면서 마름모 영역안에 들어오는지 체크
    for h in home:
        if abs(i-h[0])+abs(j-h[1])<s:
            cnt+=1
    return cnt

T = int(input())

for t in range(T):
    #초기화
    N,M = map(int,input().split())
    space = []
    home = []
    answer = 0
    for n in range(N):
        space.append(list(map(int, input().split())))
    for i in range(N):
        for j in range(N):
            if space[i][j]==1:
                home.append((i,j))

    #계산시작
    size = 1
    for i in range(N+1):
        #마름모 모양의 크기만큼 돈이 필요함
        needMoney = size*size+(size-1)*(size-1)
        for i in range(n):
            for j in range(n):
                #마름모 영역안의 home갯수를 count
                h = count_home(i,j,size,home)
                #필요한 돈을 충족시키면 answer와 비교후 큰값을 answer에 저장
                if needMoney<=h*M:
                    if answer<h:
                        answer = h

        
        size+=1

    print("#",end='')
    print(t+1,end=' ')
    print(answer)
