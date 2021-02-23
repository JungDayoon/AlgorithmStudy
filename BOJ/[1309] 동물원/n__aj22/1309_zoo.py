if __name__ == "__main__":
    N = int(input())
    cache = [[1]*3 for _ in range(N+1)]
    
    for i in range(2, N+1):
        cache[i][0] = (cache[i-1][0]+cache[i-1][1]+cache[i-1][2])%9901
        cache[i][1] = (cache[i-1][0]+cache[i-1][2])%9901
        cache[i][2] = (cache[i-1][0]+cache[i-1][1])%9901
    
    print(sum(cache[N])%9901)