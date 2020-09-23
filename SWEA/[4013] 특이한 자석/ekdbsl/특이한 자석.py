import sys

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
wheel = [[]]
score = [[0,1],[0,2],[0,4],[0,8]]

def rotate(dir, num):
    new_wheel = [0,0,0,0,0,0,0,0]

    if(dir == 1): #시계 방향
        for i in range(1, 8):
            new_wheel[i] = wheel[num][i-1]
        new_wheel[0] = wheel[num][7]
        
    else: #반시계 방향
        for i in range(0, 7):
            new_wheel[i] = wheel[num][i+1]
        new_wheel[7] = wheel[num][0]
    
    return new_wheel
        

for test_case in range(1, T + 1):
    K = int(input())
    wheel = [[int(x) for x in input().split()]for y in range(4)]
    answer = 0
    for i in range(K):
        rotateNum, rotateDir = map(int, input().split())
        rotateNum -= 1
        left_prev = rotateNum
        right_prev = rotateNum
        left_dir = rotateDir
        right_dir = rotateDir
        left = rotateNum-1
        right = rotateNum+1
        rotateInfo = [[rotateDir, rotateNum]]
        while left>=0:
            if(wheel[left][2] != wheel[left_prev][6]):
                left_dir *= -1
                rotateInfo.append([left_dir, left])
                left -= 1
                left_prev -= 1
            else:
                break
        while right < 4:
            if(wheel[right][6] != wheel[right_prev][2]):
                right_dir *= -1
                rotateInfo.append([right_dir, right])
                right += 1
                right_prev += 1
            else:
                break
        
        for j in range(len(rotateInfo)):
            wheel[rotateInfo[j][1]] = rotate(rotateInfo[j][0], rotateInfo[j][1])

    for i in range(4):
        answer += score[i][wheel[i][0]]
    print("#{} {}".format(test_case, answer))



