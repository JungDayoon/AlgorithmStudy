#초기화
n = int(input())
exp = 0
tree = [ list(input()) for _ in range(n)]
for i in range(n):
    if 2**i == n:
        exp = i
        break

def check_same(i,j,e):
    a = tree[i][j]
    b = tree[i][j+2**e]
    c = tree[i+2**e][j]
    d = tree[i+2**e][j+2**e]

    if a==b==c==d:
        if tree[i][j][0]!='(':
            return tree[i][j]
    return '('+a+b+c+d+')'

for e in range(1,exp+1):
    for i in range(0,n,2**e):
        for j in range(0,n,2**e):
            tree[i][j]=check_same(i,j,e-1)

print(tree[0][0])

