import sys
sys.stdin = open("input.txt", "r")
N = 0
M = 0
K = 0
arr1 = [] #원래 상태를 저장하는 arr
arr2 = [] #바뀌고 있는 상태를 저장하는 arr
di = [-1,0,1,0] #상 부터 시계방향으로
dj = [0,1,0,-1]
def rindex(num):
    global K
    return int(num+K)
def spread(time,nN, nM):

    while (time>0):
        time = time-1
        new_list = []
        for i in range(nN):
            for j in range(nM):

                if arr2[i][j] == 0 or arr2[i][j][1]==-1:
                    continue
                elif(arr2[i][j][1]==1):#비활성화 상태
                    arr2[i][j][0]-=1
                    if(arr2[i][j][0]==0):#비활성화 상태 X 가 끝나면
                        arr2[i][j][1] = 0 #활성화 상태로 바꿔주고
                        arr2[i][j][0] = arr1[i][j] #새로 시간 X 설정해준다.
                elif(arr2[i][j][1]==0): #활성화 상태인경우
                    if(arr2[i][j][0] == arr1[i][j]):#활성화 상태가 된 후 1시간째이면
                        for k in range(4):
                            nexti = i+di[k]
                            nextj = j+dj[k]
                            if(arr1[nexti][nextj] == 0):
                                new_list.append([nexti, nextj, arr1[i][j]])
                    arr2[i][j][0]-=1
                    if(arr2[i][j][0]==0):
                        arr2[i][j][1] = -1
                        arr2[i][j][0] = arr1[i][j]
        for k in new_list:
            if(arr2[k[0]][k[1]]==0):
                arr2[k[0]][k[1]]= [k[2],1]
                arr1[k[0]][k[1]] = k[2]
            else:
                if arr2[k[0]][k[1]][0]<k[2]:
                    arr2[k[0]][k[1]][0] = k[2]
                    arr1[k[0]][k[1]] = k[2]





T = int(input())
for test_case in range(1, T + 1):
    N, M, K = map(int, input().split())
    arr1 = [[0]*(M+K+K) for _ in range(N+K+K)]
    arr2 = [[0] * (M + K + K) for _ in range(N + K + K)]
    for i in range(N):
        newarr = list(map(int, input().split()))
        for j in range(M):
            if newarr[j]!=0:
                arr1[rindex(i)][rindex(j)] = newarr[j]
                arr2[rindex(i)][rindex(j)] = [newarr[j],1]
    time = K
    spread(time, N+K+K, M+K+K)
    num = 0
    for i in arr2:
        for j in i:
            if(j!=0):
                if(j[1]!=-1):
                    num+=1
    print("#"+str(test_case)+" "+str(num))

