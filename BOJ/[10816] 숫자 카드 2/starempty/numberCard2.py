import sys

n = int(input())
num = list(map(int, sys.stdin.readline().split()))
new_num = dict()
for i in range(n):
    try:
        new_num[num[i]] += 1
    except:
        new_num[num[i]] = 1

m = int(input())
card = list(map(int, sys.stdin.readline().split()))

for i in card:
    try:
        print(new_num[i], end=' ')
    except:
        print(0, end=' ')