N,M = map(int,input().split())
pre_problem = {}
queue = {}
check = [True for _ in range(N+1)]

for i in range(N):
    pre_problem[i+1] = []
    queue[i+1] = []
for i in range(M):
    A,B = map(int,input().split())
    pre_problem[B].append(A)

def check_pre(i):
    for p in pre_problem[i]:
        if check[p]:
            queue[p].append(i)
            return False
    return True

def find_answer(i):
    if check_pre(i):
        print(i,end=' ')
        check[i]=False
        for q in queue[i]:
            find_answer(q)

for i in range(1,N+1):
    find_answer(i)