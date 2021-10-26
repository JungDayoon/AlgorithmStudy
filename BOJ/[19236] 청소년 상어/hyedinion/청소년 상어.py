import sys
import heapq
import copy
input = sys.stdin.readline
d = [[-1,0],[-1,-1],[0,-1],[1,-1],[1,0],[1,1],[0,1],[-1,1]]
space = [ [[] for _ in range(4)] for _ in range(4)]
answer = 0
#(0,0)상어가 먹음 => shark[i,j,d], fish_heap[n,i,j,d]
# 물고기 번호대로 이동 (상어자리x, 바깥x, 물고기자리->자리바꾸기)
#상어가 이동후 먹음 (칸의 물고기를 먹고 방향을 가짐), 여러칸 가능 -> 택1
# 상어가 이동할 칸이 없으면 집에 감.
def fish_can_move(space,shark,i,j):
    if shark[0] ==i and shark[1] ==j:
        return 0
    if i<0 or j<0 or i>3 or j>3:
        return 0
    if len(space[i][j])>0:
        return 2
    else:
        return 1

def move_fish(space,f,shark,fish_heap):
    for _ in range(8):
        i,j = f[1]+d[f[3]][0],f[2]+d[f[3]][1]
        c = fish_can_move(space,shark,i,j)
        if c==0:
            f[3] = (f[3]+1)%8
            space[f[1]][f[2]][1] = f[3]
        elif c==2:
            heapq.heappush(fish_heap,[space[i][j][0],f[1],f[2],space[i][j][1]])
            space[f[1]][f[2]] , space[i][j] = space[i][j],space[f[1]][f[2]]
            break
        else:
            space[f[1]][f[2]] , space[i][j] = space[i][j],space[f[1]][f[2]]
            break
    return

def shark_can_move(space,shark):
    shark_move_list = []
    for n in range(1,4):
        i,j = shark[0]+n*d[shark[2]][0],shark[1]+n*d[shark[2]][1]
        if i<0 or j<0 or i>3 or j>3:
            return shark_move_list
        if len(space[i][j])>0:
            shark_move_list.append([i,j])
    return shark_move_list

def dfs(space,shark,size):
    global answer
    temp_size=[size]
    fish_heap = []
    for i in range(4):
        for j in range(4):
            if len(space[i][j])>0:
                heapq.heappush(fish_heap, [space[i][j][0],i,j,space[i][j][1]])
    
    f_check = [False for _ in range(17)]
    while len(fish_heap)!=0:
        f = heapq.heappop(fish_heap)
        if space[f[1]][f[2]][0]==f[0]:
            if f_check[f[0]]!=True:
                f_check[f[0]]=True
                move_fish(space,f,shark,fish_heap)
    
    shark_move_list = shark_can_move(space,shark)
    for ml in shark_move_list:
        i,j = ml[0],ml[1]
        a,b = map(int,space[i][j])
        space[i][j]=[]
        new_space = copy.deepcopy(space)
        new_shark = [i,j,b]
        temp_size.append(dfs(new_space,new_shark,size+a))
        space[ml[0]][ml[1]]=[a,b]

    size = max(temp_size)
    if answer<size:
        answer = size
    return size



#입력
for i in range(4):
    li = list(map(int,input().split()))
    for j in range(4):
        space[i][j]= [li[j*2],li[j*2+1]-1]

shark = [0,0,space[0][0][1]]
size = space[0][0][0]
space[0][0]=[]

#dfs
dfs(space,shark,size)
print(answer)