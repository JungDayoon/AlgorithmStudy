n = int(input())
numbers = [-1]+list(map(int, input().split()))
ans = [-1, numbers[1], max(numbers[1]*2, numbers[2])]

for i in range(3, n+1):
    maxi = numbers[i]
    for j in range(1,i+1):
        if maxi < numbers[j]+ans[i-j]:
            maxi = numbers[j]+ans[i-j]
    ans.append(maxi)

print(ans[n])