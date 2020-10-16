N = int(input())
time = []
in_degree = [0]*N
arr = {}
for i in range(N):
    arr[i] = []
for i in range(N):
    new_list = list(map(int, input().split()))
    time.append(new_list[0])
    for j in range(1, len(new_list)-1):
        B = new_list[j]-1
        in_degree[i]+=1
        arr[B].append(i)
time2 = [0]*N
queue = []
for i in range(N):
    if(in_degree[i] == 0):
        queue.append(i)
while(queue):
    now_node = queue.pop(0)
    check_list = arr[now_node]
    time2[now_node]+=time[now_node]
    for i in check_list:
        in_degree[i]-=1
        time2[i] = max(time2[i],time2[now_node])
        if(in_degree[i] == 0):
            queue.append(i)
for i in time2:
    print(i)