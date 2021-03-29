T = int(input())

def check_line(space,n,x):
    s = space[:]

    a = s[0]
    b = 0

    i = 0
    while(i<n):
        if s[i]!=a:
            b = s[i]

            if abs(a-b)>1:
                return 0

            #오른쪽 경사
            if a>b:
                if i+x>n:
                    return 0
            
                for j in range(x):
                    if s[i+j]!=b:
                        return 0
                    s[i+j]=-1
                i = i+x
                a = b
                continue
                

            #왼쪽 경사
            elif a<b:
                if i-x<0:
                    return 0
                for j in range(1,x+1):
                    if s[i-j]!=a:
                        return 0
                a = b

            
        i+=1

    return 1



for t in range(T):
    #초기화
    answer = 0
    n,x = map(int,input().split())
    space = [ 0 for _ in range(n)]

    for i in range(n):
        space[i] = list(map(int,input().split()))

    #row 계산 시작
    for i in range(n):
        answer += check_line(space[i],n,x)
    
    #2차원배열 뒤집기
    #column 계산 시작
    space = list(map(list,zip(*space)))
    for i in range(n):
        answer += check_line(space[i],n,x)

    print('#',end='')
    print(t+1,end=' ')
    print(answer)