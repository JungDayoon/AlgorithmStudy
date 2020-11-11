n = int(input())
a = list(map(int, input().split()))
ans = [0]*n

for i in range(0, n):
    ans[i] = 1
    for j in range(i-1, -1, -1):
        if a[i] < a[j] and ans[i] <= ans[j]:
            ans[i] = ans[j] + 1

print(ans)
print(max(ans), end='')