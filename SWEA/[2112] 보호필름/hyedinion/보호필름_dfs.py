#가능한 space인지 확인
def check_line(space,d,w,k):
    for j in range(w):
        check = 0
        cnt = 0
        #pre와 같은 숫자의 count가 K만큼 나오면 check = 1 아니면 바로 return False
        pre = space[0][j]
        for i in range(d):
            if space[i][j]==pre:
                cnt +=1
            else:
                cnt = 1
                pre = space[i][j]
            if cnt>=k:
                check=1
        if check!=1:
            return False
    return True

#space 수정후 재귀함수 호출
def change_space(space,D,W,K,lineCheck,n,startn):
    global answer
    if n>=answer:
        return answer
        
    #가능한 space이면 바로 answer 수정 후 return
    if check_line(space,D,W,K):
            answer = n
            return answer
    
    # 겹치는 경우의 수를 제거하기 위해 마지막 startn 뒤에만 변경
    for i in range(startn,D):
        s = space[i]
        
        lineCheck[i]=1
        space[i]= [1 for _ in range(W)]
        answer = change_space(space,D,W,K,lineCheck,n+1,i+1)
        space[i]= [0 for _ in range(W)]
        check = change_space(space,D,W,K,lineCheck,n+1,i+1)
        space[i] = s
        lineCheck[i]=0

    return answer


T = int(input())

for t in range(T):
    D,W,K = map(int,input().split())
    space = [ list(map(int,input().split())) for _ in range(D)]
    answer = K
    lineCheck=[0 for _ in range(D)]

    #dfs시작
    answer = change_space(space,D,W,K,lineCheck,0,0)
        
    print("#{} {}".format(t+1, answer))