def find(index, start):
    if(cache[index][start]!=-1):
        return cache[index][start]
    num = 0
    for i in range(start, 10):
        num+=find(index-1, i)
    
    cache[index][start] = num
    return num

if __name__ == "__main__":
    TC = int(input())
    cache = [[-1]*10 for _ in range(65)]
    for i in range(10):
        cache[1][i] = 1
        cache[2][i] = 10-i
    for tc in range(TC):
        n = int(input())
        answer = 0 
        for i in range(10):
            answer+=find(n, i)
    
        print(answer)