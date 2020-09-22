import sys
dx = [0,0,1,0,-1]
dy = [0,-1,0,1,0]
Battery = []

def check_d(x1, y1):
    num = []
    battery_case = len(Battery)
    for i in range(0, battery_case):
        #D = |XA – XB| + |YA – YB|
        if(abs(x1-Battery[i][0])+abs(y1-Battery[i][1])<=Battery[i][2]):
            num.append(i+1)
    return num
        
def check_charge_num(A_D, B_D):
    maxa = 0 
    maxb = 0
    a_b = list(set(A_D).intersection(B_D))

    #print(a_b, end=" ")
    if(len(a_b)==0): # 겹치지 않는 경우
        for i in A_D:
            temp = Battery[i-1][3]
            if(maxa<temp):
                maxa = temp
        for i in B_D:
            temp = Battery[i-1][3]
            if(maxb<temp):
                maxb = temp
        return maxa+maxb
    else:
        for i in A_D:
            for j in B_D:
                temp = Battery[i-1][3]+Battery[j-1][3] if i!=j else Battery[i-1][3]
                if(maxa<temp):
                    maxa = temp
        
        return maxa
        



sys.stdin = open("/Users/najihye/algo2/SWEA/[5644] 무선 충전/n__aj22/input.txt", "r")

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    M, A = map(int, input().split()) #M : 총 이동시간 A : BC의 개수
    #A이동정보
    A_move = list(map(int,input().split()))
    #print(A_move)
    #B이동정보
    B_move = list(map(int,input().split()))
    #print(B_move)
    
    for i in range(0,A):
        Battery.append(list(map(int,input().split())))
    A_X = 1
    A_Y = 1
    B_X = 10
    B_Y = 10
    A_D = check_d(A_X, A_Y)
    B_D = check_d(B_X, B_Y)
    charge_num = 0
    charge_num+=check_charge_num(A_D, B_D)
    for i in range(0,M):
        A_X = A_X+dx[A_move[i]]
        A_Y = A_Y+dy[A_move[i]]
        B_X = B_X+dx[B_move[i]]
        B_Y = B_Y+dy[B_move[i]]
        A_D = check_d(A_X, A_Y)
        B_D = check_d(B_X, B_Y)
        charge_num+=check_charge_num(A_D, B_D)
        #print(check_charge_num(A_D, B_D))

    Battery = []
    print("#", end="")
    print(test_case, end=" ")
    print(str(charge_num))
    