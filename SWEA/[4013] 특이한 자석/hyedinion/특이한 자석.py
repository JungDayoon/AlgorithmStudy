T = int(input())
#자석판의 0번째 위치가 바뀌므로 0번째 위치의 index를 저장
di = [0,0,0,0]

#0번째 위치의 index를 +1해줌
def di_plus(n):
    if di[n]==7:
        di[n]=0
    else:
        di[n]+=1

#0번째 위치의 index를 -1해줌
def di_minus(n):
    if di[n]==0:
        di[n]=7
    else:
        di[n]-=1

#n번째 톱니를 d 방향으로 회전
def move_di(n,d):
    if d<0:
        di_plus(n)
    else:
        di_minus(n)


def check_move(mag,n,d):
    #0번째 index가 바뀌기 때문에 처음에 자성이 같은지 check해주어 배열로 저장한다.
    state = [0,0,0]
    for i in range(3):
        #i번째 톱니의 2번 index와 i+1번째 톱니의 6번 index가 맞물려 있음.
        #index가 7보다 크면 다시 0으로 돌아온 것이으로 %8을 해준다.
        if mag[i][(di[i]+2)%8]!=mag[i+1][(di[i+1]+6)%8]:
            state[i] = 1

    # 자성이 같으면 톱니를 움직여 준다.
    if n==1:
        move_di(n-1,d)
        for i in range(3):
            if state[i]==1:
                d *=-1
                move_di(n+i,d)
            else:
                break
        return

    elif n==2:
        move_di(n-1,d)
        if state[0]==1:
            move_di(0,d*-1)
        for i in range(1,3):
            if state[i]==1:
                d *=-1
                move_di(n+i-1,d)
            else:
                break
        return

    elif n==3:
        move_di(n-1,d)
        if state[2]==1:
            move_di(3,d*-1)
        for i in range(1,-1,-1):
            if state[i]==1:
                d *=-1
                move_di(i,d)
            else:
                break
        return

    elif n==4:
        move_di(n-1,d)
        for i in range(2,-1,-1):
            if state[i]==1:
                d *=-1
                move_di(i,d)
            else:
                break
        return

for t in range(T):
    #초기화
    answer=0
    k = int(input())
    di=[0,0,0,0]
    mag=[]
    move =[]
    for i in range(4):
        mag.append(list(map(int,input().split())))

    for i in range(k):
        move.append(list(map(int,input().split())))

    #움직이기 시작
    for i in range(k):
        check_move(mag,move[i][0],move[i][1])


    #점수값 계산
    for i in range(4):
        if mag[i][di[i]]==1:
            answer += 2**(i)

    print('#',end='')
    print(t+1,end=' ')
    print(answer)




