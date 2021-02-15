# 4 5
# GIBSON YYYNN
# FENDER YYNNY
# EPIPHONE NNNYY
# ESP YNNNN
max_num = 0
guitar_num = 0
is_M = False
def song_num(choose_list, guitar):
    check_num = 0
    for i in range(M):
        flag = False
        for j in choose_list:
            if(song_info[j][i]=='Y'):
                flag = True
                break
        if(flag == True):
            check_num+=1
    global max_num, guitar_num
    if(max_num<check_num):
        max_num = check_num
        guitar_num = guitar
    if(max_num == M):
        is_M = True

def backtracking(target_num, choose_list, index):
    if(len(choose_list)==target_num):
        song_num(choose_list, target_num)
        return
    for i in range(index, N):
        choose_list.append(i)
        backtracking(target_num, choose_list, i+1)
        choose_list.pop(-1)
        if(is_M == True):
            break
    return 
if __name__ == "__main__":
    N, M = map(int, input().split())
    song_info = []
    can_play = False
    for i in range(N):
        guitar, song = input().split()
        song_list = list(song)
        if('Y' in song_list):
            can_play = True
        song_info.append(song_list)
    if(can_play == False):
        print(-1)
    else:
        for i in range(1, N+1):
            backtracking(i, [], 0)
            if(is_M == True):
                break
        print(guitar_num)
    