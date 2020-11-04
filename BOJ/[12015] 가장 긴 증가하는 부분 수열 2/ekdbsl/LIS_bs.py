def findLow(num):
    l = 0
    h = len(LIS)-1
    ret = 1000000

    while l <= h:
        mid = (l + h)//2
        if LIS[mid] >= num:
            if ret > mid:
                ret = mid
            h = mid - 1
        else:
            l = mid + 1

    return ret


N = int(input())
number = [int(x) for x in input().split()]
LIS = [number[0]]

for i in range(1, N):
    if LIS[len(LIS)-1] < number[i]:
        LIS.append(number[i])
    else:
        LIS[findLow(number[i])] = number[i]

print(LIS)
print(len(LIS))