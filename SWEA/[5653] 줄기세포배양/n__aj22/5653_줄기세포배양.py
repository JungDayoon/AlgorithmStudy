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
        new_list = [] #줄기세포가 퍼질 곳을 저장해두는 리스트,똑같은 위치에 두번이상 퍼질 경우를 대비해서 저장해두고 한번에 처리
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
                        for k in range(4):#주위 네 방향 확
                            nexti = i+di[k]
                            nextj = j+dj[k]
                            if(arr1[nexti][nextj] == 0):#줄기세포가 퍼질수 있는 빈곳이면
                                new_list.append([nexti, nextj, arr1[i][j]])#new_list에 저장해둠
                    arr2[i][j][0]-=1
                    if(arr2[i][j][0]==0):#활성화상태가 끝나면
                        arr2[i][j][1] = -1 #죽은세포
                        arr2[i][j][0] = arr1[i][j]
        for k in new_list:
            if(arr2[k[0]][k[1]]==0): #아직 빈공간이면
                arr2[k[0]][k[1]]= [k[2],1] #현재 위치에 새로운 줄기세포를 저장해줌, 비활성화 상태로
                arr1[k[0]][k[1]] = k[2] #시간정보만 담고 있는 곳에도 시간 저장해줌
            else:#현재 위치에 이미 줄기세포가 퍼졌다면
                if arr2[k[0]][k[1]][0]<k[2]: #더 긴 시간을 가지고 있는 세포로 변경해줌
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

