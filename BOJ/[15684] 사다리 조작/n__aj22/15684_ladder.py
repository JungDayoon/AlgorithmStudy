import copy
N, M, H = map(int, input().split())
arr = [[0]*(N-1) for _ in range(H)]
visited = [[True]*(N-1) for _ in range(H)]
min_num = 5
combi_list = []
for i in range(M):
    start,end = map(int, input().split())
    arr[start-1][end-1] = 1
    visited[start-1][end-1] = False
    if(end>1):
        visited[start-1][end-2] = False
    if(end<N-1):
        visited[start-1][end] = False
def isIn(nowr, nowc):
    #print(nowr, nowc)
    if(arr[nowr][nowc] == 0 and visited[nowr][nowc]):
        flag = 1
        if(nowc!=0):
            if(arr[nowr][nowc-1] == 0):
                flag = 1
            else:
                flag = 0
        if(nowc!=N-2):
            if(arr[nowr][nowc+1] == 0):
                flag = 1
            else:
                flag = 0
        if(flag == 1):
            return True
    return False
def make_ladder(nowcol,now_num, target_num, prev, num_list):
    if(now_num == target_num):
        temp_list = list(num_list)
        combi_list.append(temp_list)
        return 1
    for i in range(prev,H):
        if i not in num_list:
            if isIn(i, nowcol):
                num_list.append(i)
                make_ladder(nowcol, now_num+1, target_num, i+1, num_list)
                num_list.pop(-1)
    return
def check():
    for i in range(0,N-1):
        now_num = i
        for j in range(0, H):
            if(now_num<N-1):
                if(arr[j][now_num] == 1):
                    now_num+=1
                    continue
            if(now_num>0):
                if(arr[j][now_num-1] == 1):
                    now_num-=1
        if(now_num != i):
            return False
    return True



def backtracking(now_C, num):
    global min_num
    global combi_list
    if (num > 3):
        return
    if(now_C == N-1):
        if(check()):
            if(num == 0):
                print(0)
                exit()
            # for i in arr:
            #     print(i)
            # print()
            min_num = min(min_num, num)
        return

    check_sum = 0
    for i in range(H):
        check_sum+=arr[i][now_C]

    target_num = check_sum + check_sum%2 #작거나 같은 짝수를 찾아준다.
    while(True):
        if(target_num > H):
            break
        combi_list = []
        make_ladder(now_C, 0, target_num-check_sum, 0, [])
        for i in combi_list:
            for j in i:
                arr[j][now_C] = 1
                visited[j][now_C] = False
                if(now_C>0):
                    visited[j][now_C-1] = False
                if(now_C<N-2):
                    visited[j][now_C+1] = False
            backtracking(now_C+1, num+len(i))
            for j in i:
                arr[j][now_C] = 0
                visited[j][now_C] = True
                if (now_C > 0):
                    visited[j][now_C - 1] = True
                if (now_C < N - 2):
                    visited[j][now_C + 1] = True
        target_num+=2

backtracking(0, 0)
if(min_num == 5):
    print(-1)
else:
    print(min_num)