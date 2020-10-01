import sys
from itertools import combinations
import copy
sys.stdin = open("/Users/najihye/algo2/SWEA/[2383] 점심 식사시간/n__aj22/input.txt", "r")
stair = []
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
def check_time(person1, person2):
    time = 0
    first_stair_length = (-1) *(stair[0][2])
    second_stair_length = (-1) * (stair[1][2])
    num1 = 3
    num2 = 3
    for i in range(len(person1)):
        length = abs(person1[i][0]-stair[0][0])+abs(person1[i][1]-stair[0][1])
        person1[i].append(length)
        person1[i].append(False)
    for i in range(len(person2)):
        length = abs(person2[i][0]-stair[1][0])+abs(person2[i][1]-stair[1][1])
        person2[i].append(length)
        person2[i].append(False)

    while(True):
        if(len(person1)==0 and len(person2)==0):
            #print(time)
            return time
        time+=1
        for i in person1[:]: 
            if(i[2]<0):
                if(i[3] == False):
                    i[3]=True
                else:
                    i[2]-=1
            if(i[2]==first_stair_length):
                num1+=1
                person1.remove(i)
        for i in person1[:]: 
            if(i[2]==0):
                if(num1>0):
                    i[2]-=1
                    num1-=1
            elif(i[2]>0):
                i[2]-=1
            if(i[2]==first_stair_length):
                num1+=1
                person1.remove(i)
        #num1 +=nownum1
        for i in person2[:]: 
            if(i[2]<0):
                if(i[3] == False):
                    i[3]=True
                else:
                    i[2]-=1
            if(i[2]==second_stair_length):
                num2+=1
                person2.remove(i)
        for i in person2[:]: 
            if(i[2]==0):
                if(num2>0):
                    i[2]-=1
                    num2-=1
            elif(i[2]>0):
                i[2]-=1
            if(i[2]==second_stair_length):
                num2+=1
                person2.remove(i)
        #num2 +=nownum2
            
for test_case in range(1, T + 1):
    N = int(input())
    room = []
    person = []
    stair = []
    for i in range(N):
        new_room = list(map(int, input().split()))
        room.append(new_room)
        for j in range(0, N):
            if(new_room[j] == 1):
                person.append([i,j])
            elif(new_room[j]>1):
                stair.append([i,j,new_room[j]])
    person_num = len(person)
    min_time = 99999

    min_time = min(check_time(copy.deepcopy(person),[]), check_time([],copy.deepcopy(person)))
    for i in range(1, (person_num//2)+1):
        check_list = list(combinations(person,i))
        for j in check_list:
            list1 = []
            list2 = []
            for k in range(len(j)):
                #print(k)
                list1.append(j[k])
            list1 = copy.deepcopy(list1)
            for k in range(len(person)):
                if person[k] not in list1:
                    list2.append(person[k])
            list2 = copy.deepcopy(list2)

            if(i == person_num - person_num//2):
                min_time = min(min_time,check_time(list1, list2))
            else:
                list1_copy = copy.deepcopy(list1)
                list2_copy = copy.deepcopy(list2)
                min_time = min(min_time,check_time(list1, list2))
                min_time = min(min_time,check_time(list2_copy, list1_copy))
                
    print("#"+str(test_case)+" "+str(min_time))
