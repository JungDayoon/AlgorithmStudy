import sys

sys.stdin = open("/Users/najihye/algo2/SWEA/[2117] 홈 방범 서비스/n__aj22/input.txt", "r")

T = int(input())
def find_home(i, j, K, N, home):
    starty = i - K + 1 if i >= K - 1 else 0
    startx = j - K + 1 if j >= K - 1 else 0
    endy = i + K if i + K - 1 < N else N
    endx = j + K if j + K - 1 < N else N
    home_num = 0
    for a in range(starty, endy):
        for b in range(startx, endx):
            if(abs(a-i)+abs(b-j)<K):
                if(home[a][b]==1):
                    home_num+=1
    
    return home_num


for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    home = []
    home_num = 0
    for i in range(N):
        new_home = list(map(int, input().split()))
        home.append(new_home)
        home_num+=new_home.count(1)

    #print(home_num)
    K = 1
    max_home = 0
    while(True):
        need_money = K * K + (K - 1) * (K - 1)
        need_home = (need_money // M) if need_money%M == 0 else (need_money//M + 1)
        if(need_home>home_num): #종료조건 이익을 남기기 위해 필요한 집보다 현재 집의 개수가 작으면
            break

        for i in range(N):
            for j in range(N):
                find_home_num = find_home(i, j, K, N, home)
                if(find_home_num >= need_home):
                    max_home = max(max_home, find_home_num)
        K+=1
    print("#"+str(test_case)+" "+str(max_home))