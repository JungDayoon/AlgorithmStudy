def find_lis():
    L = [0]
    length = 0
    for i in arr:
        from_a, to_b = i
        if(L[-1]<to_b):
            L.append(to_b)
            length+=1
        else:
            start = 0
            end = len(L)
            while(start<end):
                mid = (start+end)//2
                if(to_b>L[mid]):
                    start = mid+1
                else:
                    end = mid
            L[start] = to_b
    return length


if __name__ == "__main__":
    N = int(input())
    arr = []
    for i in range(N):
        arr.append(list(map(int, input().split())))
    arr.sort()
    print(N-find_lis())