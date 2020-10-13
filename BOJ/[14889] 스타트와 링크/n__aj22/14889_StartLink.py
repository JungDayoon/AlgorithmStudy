from itertools import combinations
N = int(input())
min_num = 9999
arr = []
half_mem_num = N/2
dr = [-1,0,1,0]
dc = [0,1,0,-1]
visited = [[True]*N for _ in range(N)]
def inRange(nowr, nowc):
    if(nowr != nowc and nowr>=0 and nowc>=0 and nowr<N and nowc<N and visited[nowr][nowc]):
        return True
    return False
def div_team_member(nownum, member_list, prev):
    global min_num
    if(nownum == half_mem_num):
        temp = 0
        com_list = list(combinations(member_list,2))
        for i in com_list:
            temp+=arr[i[0]][i[1]]
            temp+=arr[i[1]][i[0]]

        new_member_list = []
        for i in range(N):
            if(i not in member_list):
                new_member_list.append(i)
        temp2 = 0
        com_list2 = list(combinations(new_member_list,2))
        for i in com_list2:
            temp2 += arr[i[0]][i[1]]
            temp2 += arr[i[1]][i[0]]
        min_num = min(abs(temp2-temp), min_num)
        return
    for i in range(prev, N):
        if(i not in member_list):
            member_list.append(i)
            div_team_member(nownum+1, member_list, i+1)
            member_list.pop(-1)
    return

for i in range(N):
    new_arr = list(map(int, input().split()))
    arr.append(new_arr)
div_team_member(0, [], 0)
print(min_num)


