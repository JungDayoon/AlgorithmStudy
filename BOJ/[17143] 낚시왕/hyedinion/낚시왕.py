import sys
def move_shark(shark):
    global R
    global C
    sdic = dict()
    for k,s in shark.items():
        for i in range(s[2]):
            #움직이기
            s[0]+=s[3][0]
            s[1]+=s[3][1]
            #방향전환
            if s[0]>R or s[1]>C or s[0]<1 or s[1]<1:
                s[3][0] = s[3][0]*-1
                s[3][1] = s[3][1]*-1
                s[0]+=s[3][0]*2
                s[1]+=s[3][1]*2
        #dic에 위치정보를 key로해서 담아주기
        key = str(s[0])+" "+str(s[1])
        if key not in sdic.keys():
            sdic[key] = [k]
        else:
            sdic[key].append(k)
    eat_shark(shark,sdic)
    return

def eat_shark(shark, sdic):
    for key,value in sdic.items():
        wv = 0
        winner = []
        smax = 0
        if len(value)>1:
            for v in value:
                if shark[v][4]>smax:
                    winner = shark[v]
                    wv = v
                    smax = shark[v][4]
                shark.pop(v)
            shark[wv] = winner
    return

def hook_shark(f, shark):
    mink = 0
    mins = []
    for k,s in shark.items():
        if s[1]==f:
            if len(mins)==0:
                mins = s
                mink = k
            else:
                if mins[0]>s[0]:
                    mins = s
                    mink = k
    if len(mins)!=0:
        a = mins[4]
        shark.pop(mink)
        return a
    return 0

R,C,M = map(int,sys.stdin.readline().split())
shark = dict()
for i in range(M):
    shark[i] = list(map(int,sys.stdin.readline().split()))
direction = [[0,0],[-1,0],[1,0],[0,1],[0,-1]]
for k,s in shark.items():
    if s[3]<3:
        s[2] %= R+R-2
    else:
        s[2] %= C+C-2
    s[3] = direction[s[3]][:]
answer = 0

for fisherMan in range(C):
    answer += hook_shark(fisherMan+1,shark)
    move_shark(shark)

print(answer)