def tile(width):
    #기저사례 
    if(width%2 == 1):
        return 0 
    elif(width == 2):
        return 3
    elif(width == 0):
        return 1
    if(cache[width]!= -1):
        return cache[width]
    num = 3*tile(width-2)
    #tile(2)*tile(width-2)
    for i in range(width-4+1):
        num+=2*tile(i)
    cache[width] = num
    return num
if __name__ == "__main__":
    N = int(input())
    cache = [-1]*(N+1)
    print(tile(N))
