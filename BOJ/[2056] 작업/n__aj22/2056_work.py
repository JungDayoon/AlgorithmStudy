N = int(input())

time_list = [[0,0]]
max_answer = 0
for i in range(1, N+1):
    input_list = list(map(int,input().split()))
    t = input_list[0]
    prev_num = input_list[1]
    
    if(prev_num==0):
        time_list.append([0,t])
    else:
        prev_list = input_list[2:]
        check_list =[]
        max_time = 0
        for j in range(1, len(time_list)):
            if(j in prev_list):
                max_time = max(max_time, time_list[j][1])
        time_list.append([max_time, max_time+t])
    max_answer = max(max_answer, time_list[i][1])

print(max_answer)
