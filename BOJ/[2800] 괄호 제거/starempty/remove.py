string = input()

stack = []
tmp = []
idx = [0]*len(string)
cnt = 0
for i, w in enumerate(string):
    if w == '(':
        cnt += 1
        idx[i] = cnt
        tmp.append(cnt)
    elif w == ')':
        idx[i] = (tmp.pop())
#print(idx)
answer = []
ans = ''
for i, value in enumerate(idx):
    if value == 0:
        ans += string[i]
answer.append(ans)

for i in range(1, cnt+1):
    ans = ''
    for j, value in enumerate(idx):
        if value != i:
            ans += string[j]
    answer.append(ans)

for i in range(len(answer)-1, -1, -1):
    print(answer[i])