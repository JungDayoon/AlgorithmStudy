if __name__ == "__main__":
    N = int(input())
    arr = list(map(int, input().split()))

    arr.sort()
    start, end = 0, N-1
    answer = [2000000000, 0, N-1]
    while(start<end):
        liquid_sum = arr[start]+arr[end]

        if(abs(liquid_sum)<answer[0]):
            answer = [abs(liquid_sum), arr[start], arr[end]]
        
        if liquid_sum>0:
            end-=1
        elif liquid_sum<0:
            start+=1
        else:
            break
    print(answer[1], answer[2])