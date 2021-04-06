import heapq
if __name__ == "__main__":
    N = int(input())
    train_line = []
    queue = []
    answer = 0
    for i in range(N):
        u, v = map(int, input().split())
        if(u<v):
            train_line.append([v, u])
        else:
            train_line.append([u, v])
    
    target_length = int(input())
    train_line.sort()
    for i in range(N):
        end, start = train_line[i]
        line_start = end-target_length
        if(end-start > target_length):continue

        heapq.heappush(queue, [start, end])
        while(queue):
            if(queue[0][0]<line_start):
                heapq.heappop(queue)
            else:
                break
        answer = max(answer, len(queue))
    print(answer)