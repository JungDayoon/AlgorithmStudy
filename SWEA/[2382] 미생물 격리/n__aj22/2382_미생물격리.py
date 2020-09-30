# import sys
# sys.stdin = open("/Users/najihye/algo2/SWEA/[2382] 미생물 격리/n__aj22/input.txt", "r")

dx = [0,0,0,-1,1]
dy = [0,-1,1,0,0]
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N, M, K = map(int, input().split())
    table =[]

    for i in range(K):
        table.append(list(map(int,input().split())))
    for i in range(M):
        word_cnt = dict() 
        for j in table:
            j[1] = j[1]+dx[j[3]]
            j[0] = j[0]+dy[j[3]]
            if(j[1] == 0 or j[0]==0 or j[1] == N-1 or j[0] == N-1):
                j[2] = j[2]//2
                if(j[3]==1):
                    j[3]=2
                elif(j[3]==2):
                    j[3]=1
                elif(j[3]==3):
                    j[3] = 4
                elif(j[3]==4):
                    j[3]=3
            key = str(j[0])+" "+ str(j[1])
            if key not in word_cnt.keys(): 
                word_cnt[key] = 1 
            else: 
                word_cnt[key] += 1
        check_list = []
        for key, value in word_cnt.items():
            if(value != 1):
                check_list.append(list(map(int,key.split())))
        
        for j in check_list:
            max_num = 0
            max_dir=0
            total = 0
            for k in table[:]:
                if(j[0]== k[0] and j[1] == k[1]):
                    if(max_num<k[2]):
                        max_num = k[2]
                        max_dir = k[3]
                    total+=k[2]
                    table.remove(k)
            table.append([j[0], j[1], total, max_dir])
    answer = 0
    for i in table:
        answer+=i[2]
    print("#"+str(test_case)+" "+str(answer))