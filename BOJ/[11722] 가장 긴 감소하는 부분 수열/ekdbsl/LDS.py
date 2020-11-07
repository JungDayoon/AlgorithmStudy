N = int(input())
number = [int(x) for x in input().split()]
cnt = [1 for _ in range(N)]

for i in range(N-2, -1, -1):
    for j in range(i+1, N):
        if number[i] > number[j]:
            if cnt[i] < cnt[j]+1:
                cnt[i] = cnt[j]+1

print(max(cnt))
