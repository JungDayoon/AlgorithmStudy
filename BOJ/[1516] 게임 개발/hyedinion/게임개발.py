import sys
input = sys.stdin.readline
N = int(input())
tower = [[] for _ in range(N)]
check = [False for _ in range(N)]
tower_stack = [[] for _ in range(N)]
answer = [0 for _ in range(N)]

#pre 없으면 자기자신
#pre 있으면 max(current_time,answer[pre]) 더하기 자기자신

def check_pre(t):
    time = 0
    for pre in t[1]:
        if check[pre-1]==False:
            tower_stack[pre-1].append(t)
            return [False]
        time = max(time,answer[pre-1])
    return [True,time]

def build_tower(t):
    I = t[2]
    temp = check_pre(t)
    if not temp[0]:
        return
    time = temp[1]
    check[I]=True
    answer[I]=time+t[0]
    for new_tower in tower_stack[I]:
        build_tower(new_tower)

for i in range(N):
    temp = list(map(int,input().split()))
    tower[i] = [temp[0],temp[1:-1],i]
tower.sort()

for t in tower:
    build_tower(t)

for i in range(N):
    print(answer[i])