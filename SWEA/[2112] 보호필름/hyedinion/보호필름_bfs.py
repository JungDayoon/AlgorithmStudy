#가능한 space인지 확인
def check_line(lineCheck,d,w,k):
    global space

    #lineCheck대로 s 설정
    s = [[]for _ in range(D)]
    for j in range(D):
        if lineCheck[j]==-1:
            s[j] = space[j]
        elif lineCheck[j]==0:
            s[j] = [0]*w
        else:
            s[j]=[1]*w


    for j in range(w):
        check = 0
        cnt = 0
        #pre와 같은 숫자 count가 K만큼 나오면 check = 1
        pre = s[0][j]
        for i in range(d):
            if s[i][j]==pre:
                cnt +=1
            else:
                cnt = 1
                pre = s[i][j]
            if cnt>=k:
                check=1
                break
        if check!=1:
            return False
    return True


#linecheck수정해주고 queue에 저장
def change_space(lineCheck,n,sn):
    global space
    global queue
    global D
    global W
    global K

    #만약 K보다 작은 수 중 답이 없으면 K가 정답이기 때문에 -1 return => backTracking
    if n>=K:
        return -1

    # 겹치는 경우의 수를 제거하기 위해 sn 뒤에만 변경
    append = queue.append
    for i in range(sn,D):
        lineCheck[i]=1
        #check하고 아니면 queue에 담기
        if check_line(lineCheck,D,W,K):
            return n
        else:
            if n+1<K:
                append([lineCheck[:],n+1,i+1])

        lineCheck[i]=0
        if check_line(lineCheck,D,W,K):
            return n
        else:
            if n+1<K:
                append([lineCheck[:],n+1,i+1])
        lineCheck[i]=-1

    return -1


T = int(input())

for t in range(T):
    D,W,K = map(int,input().split())
    space = [list(map(int,input().split())) for _ in range(D)]
    answer = 0
    #각 라인을 어떤 값으로 수정했는지 저장
    lineCheck=[-1 for _ in range(D)]
    queue=[]

    #bfs시작
    if not check_line(lineCheck,D,W,K):
        queue.append([lineCheck,1,0])

    while len(queue)!=0:
        l,n,sn = queue.pop(0)
        answer = change_space(l,n,sn)
        if answer!=-1:
            break
    if answer ==-1:
        answer = K
        
    print("#{} {}".format(t+1,answer))

