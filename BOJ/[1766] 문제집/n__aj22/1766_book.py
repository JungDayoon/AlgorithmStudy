N, M = map(int, input().split())
in_degree = [0]*(N+1)
arr = {}

if __name__ == "__main__" :
    for i in range(N):
        arr[i+1] = []
    for i in range(M):
        A, B = map(int, input().split())
        arr[A].append(B)
        in_degree[B]+=1
    queue = []
    for i in range(1,N+1):
        if(in_degree[i] == 0):
            queue.append(i)
    answer = []
    while(queue):
        num = queue.pop(queue.index(min(queue)))
        print(num, end = ' ')

        check_list = arr[num]
        for i in check_list:
            in_degree[i]-=1
            if(in_degree[i] == 0):
                queue.append(i)
