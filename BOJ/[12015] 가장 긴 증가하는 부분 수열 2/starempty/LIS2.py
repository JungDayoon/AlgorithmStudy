n = int(input())
num = list(map(int, input().split()))
dp = []


def bs(cmp):
    left = 0
    right = len(dp)-1
    while left <= right:
        mid = (left+right)//2
        if dp[mid] < cmp:
            left = mid + 1
        else:
            right = mid - 1
    return left


for i in num:
    tmp = bs(i)
    #print(tmp)
    if len(dp) <= tmp:
        dp.append(i)
    else:
        dp[tmp] = i
    #print(dp)
print(len(dp))