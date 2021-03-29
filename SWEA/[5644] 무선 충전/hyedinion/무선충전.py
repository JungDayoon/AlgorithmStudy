T = int(input())
move = [[0,0],[0,-1],[1,0],[0,1],[-1,0]]
result=[]

def find_max(AP,A,ad,bd):
    alis=[[0,-1]]
    blis=[[0,-1]]
    sum=0

    #만약 현재 ad(a의좌표),bd가 ap의 범위내에 있으면 alist, blist에 ap정보 저장
    for i in range(A):
        if ad in AP[i][0] :
            alis.append([AP[i][1],i])
        if bd in AP[i][0] :
            blis.append([AP[i][1],i])

    #power순으로 정렬, 같은 값의 power를 가진 ap를 비교하기 위해 ap번호도 같이 저장
    alis = sorted(alis,key =lambda x:x[0],reverse=True)
    blis = sorted(blis,key =lambda x:x[0],reverse=True)


    #근처에 해당하는 ap값이 없을 때 비교
    if alis[0][0]==0:
        if blis[0][0]==0:
            return 0
        else:
            return blis[0][0]
    else:
        if blis[0][0]==0:
            return alis[0][0]
    
    
    #ap가 있을 때 비교, ap가 같을 때 처리
    if alis[0][1]==blis[0][1]:
        #두번째 값으로 비교하기 위해 처음에 초기값 지정.
        if alis[1][0]>blis[1][0]:
            sum = alis[1][0]+blis[0][0]
        else:
            sum = alis[0][0]+blis[1][0]
    else:
        sum = alis[0][0]+blis[0][0]

    return sum


for t in range(T):
    #초기화
    Ad = [1,1] #A direction
    Bd = [10,10]
    power = 0
    Apower = []
    Bpower =[]
    M,A = map(int,input().split())
    move_A = list(map(int,input().split()))
    move_B = list(map(int,input().split()))

    AP = [ [[],0] for _ in range(A)]
    
    #ap값 받아오기
    for a in range(A):
        r,c,n,p = map(int,input().split())
        #ap의 범위에 해당하는 좌표를 배열로 저장.
        for i in range(-n,n+1):
            for j in range(-n,n+1):
                if abs(i)+abs(j)>n:
                    continue
                else:
                    AP[a][0].append([r+i,c+j])

        AP[a][1]=p
    

    #0초일때
    power += find_max(AP,A,Ad,Bd)

    #계산시작
    for sec in range(M):
        Ad = [x+y for x,y in zip(Ad,move[move_A[sec]])]
        Bd = [x+y for x,y in zip(Bd,move[move_B[sec]])]
        power += find_max(AP,A,Ad,Bd)

    print('#',end='')
    print(t+1,end=' ')
    print(power)




