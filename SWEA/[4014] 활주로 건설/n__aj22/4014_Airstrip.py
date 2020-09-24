# import sys
# sys.stdin = open("/Users/najihye/algo2/SWEA/[4014] 활주로 건설/n__aj22/input.txt", "r")
T = int(input())

def isFloat(s):
    num = s
    if(s - int(num))==0:
        return False
    else:
        return True
#중복되는 숫자 제거하고 [숫자, 갯수]형태로 바꾸기
def list_overlap_del(input_list):
    result_list = []
 
    for i in range(len(input_list)):
        list_size = len(result_list)-1
        if i == 0:
            result_list.append([input_list[i],1,0])                        
        elif result_list[list_size][0] != input_list[i]:
            result_list.append([input_list[i],1,0])  
        elif result_list[list_size][0] == input_list[i]:
            result_list[list_size][1] += 1         
    return result_list
 
def check_possibility(road, X):
    clean_road = list_overlap_del(road)
    clean_road_num = len(clean_road)
    i=0
    while True:
        if(i==clean_road_num-1):
            break
        if(isFloat(clean_road[i][0])):
            if((clean_road[i+1][0]>clean_road[i][0]) and clean_road[i][2]==-1):
                return 0
        compare_num = int(clean_road[i][0])
        if(abs(compare_num-clean_road[i+1][0])>1):
            return 0;
        if(compare_num>clean_road[i+1][0]):
            if(clean_road[i+1][1]<X):
                return 0
            else:
                insert_num = (compare_num+clean_road[i+1][0])/2
                clean_road[i+1][1]-=X
                #clean_road[i][1]+=X
                if(clean_road[i+1][1]==0):
                    del clean_road[i+1]
                    clean_road_num-=1
                clean_road.insert(i+1,[insert_num, X,-1])
                clean_road_num+=1
                
                
        elif(compare_num<clean_road[i+1][0]):
            if(clean_road[i][1]<X):
                return 0
            else:
                insert_num = (compare_num+clean_road[i+1][0])/2
                clean_road[i][1]-=X
                clean_road.insert(i+1,[insert_num,X,1])
                clean_road_num+=1
                if(clean_road[i][1]==0):
                    del clean_road[i]
                    clean_road_num-=1
        i+=1
    clean_road_num = len(clean_road)
    for i in range(0, clean_road_num-1):
        if(abs(clean_road[i][0]-clean_road[i+1][0])>1):
            return 0

    return 1
for test_case in range(1, T + 1):
    road = []
    sumofroad = 0
    N, X = map(int, input().split())  
     
    for i in range(0, N):
        new_road = list(map(int, input().split()))
        road.append(new_road)
     
    for i in range(0, N):
        check_list = []
        for j in range(0, N):
            check_list.append(road[i][j])
        sumofroad+=check_possibility(check_list,X)
    for i in range(0, N):
        check_list = []
        for j in range(0, N):
            check_list.append(road[j][i])
        sumofroad+=check_possibility(check_list,X)
         
    print("#"+str(test_case)+" "+str(sumofroad))