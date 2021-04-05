import heapq
if __name__ == "__main__":
    N = int(input())
    queue = []
    for i in range(N):
        arr = list(map(int, input().split()))
        for j in range(N):
            heapq.heappush(queue, arr[j])
            if(len(queue)>N):
                heapq.heappop(queue)
    
    print(queue[0])