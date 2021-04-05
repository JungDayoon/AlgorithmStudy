def check_time(workList):
    global time
    timeList = []
    t,num = workList[0],workList[1]
    if num==0:
        return t
    else:
        for i in range(num):
            #선행되는 작업의 시간을 time에서 찾아 timelist에 append해줌
            timeList.append(time[workList[2+i]-1])
        #가장 오래걸리는 선행작업시간을 return해줌
        return max(timeList)+t

#초기화
N = int(input())

time = []
for n in range(N):
    time.append(check_time(list(map(int,input().split()))))

print(max(time))