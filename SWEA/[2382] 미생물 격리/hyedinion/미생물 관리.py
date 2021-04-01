T = int(input())
move_d = [(0,0),(-1,0),(1,0),(0,-1),(0,1)]

def reverse_direction(n):
    if n%2==0:
        return n-1
    else:
        return n+1

def bound(microb,outline):
    zero = []
    for k in range(len(microb)):
        if microb[k][0] in outline or microb[k][1] in outline:
            microb[k][2] = microb[k][2]//2
            microb[k][3] = reverse_direction(microb[k][3])
            zero.append(microb[k])

    for z in zero:
        if z[2]==0:
            microb.remove(z)
    return

def meet(microb,m_dic):
    for key,value in m_dic.items():
        if len(value) != 1:
            i,j = map(int,key.split())
            max_num = 0
            n = 0
            d = 0
            for m in value:
                n+=m[2]
                if max_num<m[2]:
                    max_num = m[2]
                    d = m[3]
                microb.remove(m)
            microb.append([i,j,n,d])
    return

def move_to(microb,outline):
    global move_d
    m_dic = dict()
    for k in range(len(microb)):
        d = microb[k][3]
        microb[k][0]+=move_d[d][0]
        microb[k][1]+=move_d[d][1]
        key = str(microb[k][0])+" "+str(microb[k][1])
        if key not in m_dic.keys():
            m_dic[key]=[microb[k]]
        else:
            m_dic[key].append(microb[k])

    bound(microb,outline)
    meet(microb,m_dic)

    return
    


#초기화
for t in range(T):
    answer = 0
    N,M,K = map(int, input().split())
    #미생물 정보
    microb = []
    for k in range(K):
        microb.append(list(map(int,input().split())))
    #경계
    outline = [0,N-1]
    #움직이기
    for m in range(M):
        move_to(microb,outline)
    #결과출력
    for k in range(len(microb)):
        answer+= microb[k][2]
    print('#', end='')
    print(t+1,end=' ')
    print(answer)