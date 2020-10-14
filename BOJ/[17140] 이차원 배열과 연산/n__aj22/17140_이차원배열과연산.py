R = 3
C = 3
r, c, k = map(int,input().split())
arr = []
for i in range(3):
    arr.append(list(map(int, input().split())))

def check_list_num(nowarr):
    new_dic = {} #딕셔너리 생성
    for i in range(len(nowarr)):
        if(nowarr[i]!=0):
            if(nowarr[i] not in new_dic.keys()):
                new_dic[nowarr[i]]=1
            else:
                new_dic[nowarr[i]]+=1
    new_dic = sorted(new_dic.items(), key=lambda x: (x[1],x[0])) #횟수 기준 오름차순 -> 숫자 기준 오름차순
    new_arr = []
    for i in new_dic:
        new_arr.append(i[0])
        new_arr.append(i[1])
    return new_arr


time = 0
while(True):
    if(r<=R and c<=C):
        if(arr[r-1][c-1] == k):
            print(time)
            break
    if(time == 100):
        print(-1)
        break
    time+=1
    if(R>=C):
        arr2 = []
        #R 연산: 배열 A의 모든 행에 대해서 정렬을 수행한다. 행의 개수 ≥ 열의 개수인 경우에 적용된다.
        max_C = C
        for i in range(R):
            arr[i] = check_list_num(arr[i])
            if(i == 0):
                max_C = len(arr[i])
            else:
                max_C = max(max_C, len(arr[i]))

        for i in range(R):
            now_list = arr[i]
            if (len(now_list) < max_C):
                for i in range(max_C-len(now_list)):
                    now_list.append(0)
        C = max_C


    else:
        #C 연산: 배열 A의 모든 열에 대해서 정렬을 수행한다. 행의 개수 < 열의 개수인 경우에 적용된다.
        arr2 = []
        max_R = R
        for i in range(C):
            new_arr = []
            for j in range(R):
                new_arr.append(arr[j][i])
            new_arr = check_list_num(new_arr)
            arr2.append(new_arr)
            if(i==0):
                max_R = len(new_arr)
            else:
                max_R = max(max_R, len(new_arr))
        if(max_R>R):
            for i in range(max_R-R):
                arr.append([0]*C)
        else:
            for i in range(R-max_R):
                arr.pop(-1)
        R = max_R
        for i in range(C):
            insert_arr = arr2[i]
            for j in range(R):
                if(j>=len(insert_arr)):
                    arr[j][i] = 0
                else:
                    arr[j][i] = insert_arr[j]