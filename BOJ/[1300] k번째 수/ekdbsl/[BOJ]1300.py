N = int(input())
k = int(input())

answer = 0
low = 0
high = k

while low <= high:
    count = 0
    mid = (low+high)//2

    for i in range(1, N+1):
        count += min(mid//i, N)
    
    if(count < k):
        low = mid + 1
    else:
        answer = mid
        high = mid -1

print(answer)