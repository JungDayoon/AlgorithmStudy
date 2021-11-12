import sys
from collections import deque
input = sys.stdin.readline

N,M,H = map(int,input().split())
check = [ [True]*(N+1) for _ in range(H+1) ]
answer = -1
for i in range(M):
    A,B = map(int,input().split())
    check[A][B] = False

def check_answer(check):
    for i in range(1,N+1):
        currentJ = i
        for currentI in range(1,H+1):
            if not check[currentI][currentJ]:
                currentJ+=1
            elif not check[currentI][currentJ-1]:
                currentJ-=1
        if currentJ!=i:
            return False
    return True

queue = deque()
queue.append([1,1,0,[]])#I,J,depth,새로운사다리
while(queue):
    I,J,depth,append_list = queue.popleft()
    for a in append_list:
        check[a[0]][a[1]]=False
    if check_answer(check):
        answer = depth
        break
    if depth<3:
        for i in range(I,H+1):
            for j in range(1,N):
                if I==i and J>j:
                    continue
                if check[i][j] and check[i][j-1] and check[i][j+1]:
                    new_append_list = append_list[:]
                    new_append_list.append([i,j])
                    queue.append([i,j,depth+1,new_append_list])
    for a in append_list:
        check[a[0]][a[1]]=True
    
print(answer)