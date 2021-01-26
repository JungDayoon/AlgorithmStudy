def is_possible(paper1, paper2): #paper1 : 젤 위의 색종이, paper2 : 이제 놓으려고 하는 색종이
    if(paper1[0]>paper2[0] or paper1[1]>paper2[1]):
        return False
    return True
def find(i):
    if cnt[i]:
        return cnt[i]
    cnt[i] = 1

    for j in range(N):
        if(i!=j and is_possible(color_paper[i], color_paper[j])):
            cnt[i] = max(cnt[i], find(j)+1)
    return cnt[i]

N = int(input())
color_paper = [sorted(map(int, input().split()))for _ in range(N)]
cnt = [0]*N
for i in range(N):
    cnt[i] = find(i)
print(max(cnt))