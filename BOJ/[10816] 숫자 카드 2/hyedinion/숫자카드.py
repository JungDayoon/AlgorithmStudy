from collections import Counter
N = int(input())
card = list(map(int,input().split()))
M = int(input())
check = list(map(int,input().split()))

count_card = Counter(card)
answer = [0 for _ in range(M)]

for i in range(M):
    if check[i] in count_card:
        answer[i] = count_card[check[i]]

for i in range(M):
    print(answer[i],end=" ")