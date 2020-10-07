import copy
import sys
flag = False
sys.stdin = open("/Users/najihye/algo2/SWEA/[2112] 보호필름/n__aj22/input.txt", "r")

def change(now_row, index):
    for i in range(len(now_row)):
        now_row[i] = index
def save(now_row,temp_row):
    #print(temp_row)
    for i in range(len(now_row)):
        temp_row[i] = now_row[i]
def check_possibility(film, D, K):
    W = len(film[0])
    if(D >=1):
        for i in range(W):
            check_flag = False
            count = 1
            for j in range(1, D):
                if(film[j][i] == film[j-1][i]):
                    count += 1
                else:
                    count = 1
                if(count == K):
                    check_flag = True
                    break
            if(check_flag == False):
                return 0
    return 1   

def set_drug(drug_max, drug_now, film, D, K, choose_list, prev):
    global flag 
    if(drug_max == drug_now):
        #가능한지 확인하는 함수 호출
        if(check_possibility(film, D, K) == 1):
            flag = True
        return 1
    
    for i in range(prev+1, D):
        if not i in choose_list:
            choose_list.append(i)
            temp_row = [0] * len(film[0])
            save(film[i],temp_row)
            #check_film = copy.deepcopy(film)
            change(film[i], 0)
            set_drug(drug_max, drug_now+1, film, D, K, choose_list, i)
            if(flag == True):
                return 1
            change(film[i], 1)
            set_drug(drug_max, drug_now+1, film, D, K, choose_list, i)
            if(flag == True):
                return 1
            choose_list.remove(i)
            save(temp_row, film[i])

T = int(input())
for test_case in range(1, T + 1):

    D, W, K = map(int, input().split())
    film = []
    for i in range(D):
        film.append(list(map(int, input().split())))
    flag = False
    for i in range(D):
        set_drug(i, 0, film,D, K,[], -1)#i:약물 투약 횟수 max, 0 : 현재 약물 투약 횟수,film : film 내용 , [] : 약물 투약되기로 선택된 행 
        if(flag == True):
            break
    print("#"+str(test_case)+" " +str(i))
    
