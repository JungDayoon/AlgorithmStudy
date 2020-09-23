
#import sys
def turn_right(magnet):
    temp = magnet[7]
    for i in range(6, -1, -1):
        magnet[i+1] = magnet[i]
    magnet[0] = temp;
    return magnet
def turn_left(magnet):
    temp = magnet[0]
    for i in range(0, 7):
        magnet[i] = magnet[i+1]
    magnet[7]=temp
    return magnet

#sys.stdin = open("/Users/najihye/algo2/SWEA/[4013] 특이한 자석/n__aj22/input.txt", "r")
T = int(input())
for test_case in range(1, T + 1):
    rotation_T = int(input())
    magnet = []
    magnet_rotation = []
    for i in range(0, 4):
        new_magnet = list(map(int, input().split()))
        magnet.append(new_magnet)
    for i in range (0, rotation_T):
        magnet_num, rotation_dir = map(int, input().split())
        magnet_num = magnet_num-1
        magnet_rotation = []
        magnet_rotation.append([magnet_num, rotation_dir])
        now_magnet = magnet_num
        now_rotation = rotation_dir
        
        #왼쪽으로 확인
        while(now_magnet>0):
            now_rotation = 1 if now_rotation==-1 else -1
            if(magnet[now_magnet][6]!=magnet[now_magnet-1][2]):
                magnet_rotation.append([now_magnet-1, now_rotation])
            else:
                break
            now_magnet = now_magnet-1

        now_magnet = magnet_num
        now_rotation = rotation_dir
        #오른쪽으로 확인
        while(now_magnet<3):
            now_rotation = 1 if now_rotation==-1 else -1
            if(magnet[now_magnet][2]!=magnet[now_magnet+1][6]):
                magnet_rotation.append([now_magnet+1, now_rotation])
            else:
                break
            now_magnet = now_magnet+1

        for i in magnet_rotation:
            if(i[1]==1):#오르쪽으로 회전
                magnet[i[0]] = turn_right(magnet[i[0]])
            elif(i[1]==-1):
                magnet[i[0]] = turn_left(magnet[i[0]])
    score = [1,2,4,8]
    sum = 0
    for i in range(0, 4):
        sum+=score[i] if magnet[i][0]==1 else 0
    print("#"+str(test_case)+" "+str(sum))





