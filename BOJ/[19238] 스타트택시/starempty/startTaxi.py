import sys
from collections import deque

n, m, f = map(int, sys.stdin.readline().split())
info = []
for _ in range(n):
    tmp = list(map(int, input().split()))

    for j in range(len(tmp)):
        if tmp[j] == 1:
            tmp[j] = -1
    info.append(tmp)

tmp = list(map(int, input().split()))
x = tmp[0] - 1
y = tmp[1] - 1
#print(x,y)
p = ['dummy'] + [[] for _ in range(m)]

for i in range(1, m+1):
    x1, y1, x2, y2 = map(int, input().split())
    try:
        info[x1 - 1][y1 - 1].append(i)
    except:
        info[int(x1 - 1)][int(y1 - 1)] = i

    p[i] = [x2-1, y2-1]
#print(info)
dx = [1,-1,0,0]
dy = [0,0,-1,1]
def moving(x, y):
    global f
    q1.append([x,y])
    c1[x][y], cnt = 1, 0
    while q1:
        cntq = len(q1)
        temp = []
        cnt += 1
        if cnt >= f:  # 가진 연료보다 더 많이 움직여야하는 경우
            return 0
        for _ in range(cntq):
            x, y = q1.popleft()
            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                if 0 <= nx < n and 0 <= ny < n:
                    if info[nx][ny] != -1 and c1[nx][ny] == 0:
                        if info[nx][ny] > 0:
                            temp.append([nx, ny])
                        q1.append([nx,ny])  # 이동 예정 후보들(승객) 모두 저장
                        c1[nx][ny] = 1
        if temp:
            break  # 승객찾을 때까지 cnt 증가시키며 while문 반복
    if not temp:  # 현재 위치에서 찾을 고객이 없는 경우
        return 0

    f -= cnt  # 손님 찾는데 쓴 만큼 연료 빼기
    temp = sorted(temp)  # 거리가 같으면 손님을 행,열 순으로 받아야하니까(리스트 안 리스트 순 정렬)

    x, y = temp[0]  # temp 형태 [x, y]
    res = findC(x, y, info[x][y])

    if res == 0:  # 움직일 수 없는 경우
        return 0
    length, nx, ny = res
    f += length
    info[x][y] = 0  # 손님 이동 완료
    return nx, ny


def findC(x, y, idx):
    q2.append([x,y])
    c2[x][y] = 0
    while q2:
        x, y = q2.popleft()
        if c2[x][y] >= f:
            return 0  # 목적지까지 연료가 부족하면 0
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < n:
                if info[nx][ny] != -1 and c2[nx][ny] == -1:
                    q2.append([nx, ny])
                    c2[nx][ny] = c2[x][y] + 1  # length는 -1부터 시작했기 때문에 ++ 필
                    if [nx, ny] == p[idx]:  # 목적지 도달
                        return c2[nx][ny], nx, ny  # length, x, y
    return 0  # q2가 빈 경우(이동 할 곳이 없는 경우)


for _ in range(m):  # deque(): 빈 큐 만들기
    q1, c1 = deque(), [[0 for _ in range(n)] for _ in range(n)]
    q2, c2 = deque(), [[-1 for _ in range(n)] for _ in range(n)]

    if info[x][y] > 0:  # 택시 위치 == 승객 위치
        res = findC(x, y, info[x][y])
        #print(res, end='!')
        if res == 0:
            print(-1)
            sys.exit()
        length, nx, ny = res
        if length > f:
            print(-1)
            sys.exit()  # 연료가 더드는 경우
        f += length  # 연료 - 이동연료 + 이동연료*2 = 연료 + 이동연료
        info[x][y] = 0  # 이동완료 안해줘서 삽질
        x, y = nx, ny
        continue  # 승객이동 완료; 포문 넘기기
    res = moving(x, y)  # 가장 가까운 승객을 찾아서 데려다 주고나서의 위치값을 반환
    if res == 0:
        print(-1)
        sys.exit()
    else:
        x, y = res  # 승객 찾으러 다시 가기


print(f, end='')
