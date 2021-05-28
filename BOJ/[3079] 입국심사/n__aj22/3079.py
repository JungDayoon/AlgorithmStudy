if __name__ == "__main__":
    N, M = map(int, input().split())
    t = []
    for i in range(N):
        t.append(int(input()))
    
    min_t, max_t = 0, M*max(t)

    while(min_t+1 < max_t):
        mid_t = (min_t+max_t)//2
        count = 0

        for i in range(N):
            count += (mid_t//t[i])
        
        if(count>=M):
            max_t = mid_t
        else:
            min_t = mid_t
    
    print(max_t)