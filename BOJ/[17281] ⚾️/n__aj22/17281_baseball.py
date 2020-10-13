# 안타: 1
# 2루타: 2
# 3루타: 3
# 홈런: 4
# 아웃: 0
import sys
from itertools import permutations
N = int(sys.stdin.readline())

member = []
count = 0
max_score = 0
def calculate_score(member_list):

    start_member = 0
    score = 0
    global max_score
    score_list = [0]*9
    for i in range(N):
        #base = [0] * 4
        b1 = 0
        b2 = 0
        b3 = 0
        now_member = member[i]
        out_number = 0
        while(True):
            if (out_number == 3):
                break
            #skill =
            if(now_member[member_list[start_member]] == 0):
                out_number+=1
                b0 = 0
            elif(now_member[member_list[start_member]] == 1):
                score+=(b3)
                b1, b2, b3 = 1, b1, b2
            elif (now_member[member_list[start_member]] == 2):
                score+=(b3+b2)
                b1, b2, b3 = 0, 1, b1
            elif (now_member[member_list[start_member]] == 3):
                score+=(b3+b2+b1)
                b1, b2, b3 = 0, 0, 1
            elif (now_member[member_list[start_member]] == 4):
                score += (b3+b2+b1+1)
                b1, b2, b3 = 0, 0, 0
            start_member+=1
            if(start_member == 9):
                start_member = 0
    max_score = max(max_score, score)

def permutation(arr, start, end):
    global count
    if(start == end):
        new_arr = []
        for i in arr:
            new_arr.append(i)
        new_arr.insert(3, 0)
        calculate_score(new_arr)
        return
    else:
        for i in range(start, end+1):
            temp = arr[start]
            arr[start] = arr[i]
            arr[i] = temp

            permutation(arr, start+1, end)

            temp = arr[start]
            arr[start] = arr[i]
            arr[i] = temp
    return
for i in range(N):
    member.append(list(map(int, sys.stdin.readline().split())))
#calculate_score(member)
for line_up in permutations(range(1,9),8):
    line_up = list(line_up[0:3])+[0]+ list(line_up[3:])
    calculate_score(line_up)
# permutation([1,2,3,4,5,6,7,8],0, 7)
print(max_score)


