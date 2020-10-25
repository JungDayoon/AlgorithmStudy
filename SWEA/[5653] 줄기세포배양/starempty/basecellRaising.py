dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

T = int(input())

for tc in range(1, T+1):
    n,m,k = map(int, input().split())
    # 활성시간을 배열 한 줄 사이즈로 한다.
    info = [list(map(int, input().split())) + [0]*k for _ in range(n)] + [[0]*(m+k) for _ in range(k)]
    live = ['dummy'] + [[] for _ in range(10)]  # 생명력 순으로 넣을 리스트

    for i in range(n):
        for j in range(m):
            if info[i][j]:
                live[info[i][j]].append([i,j,info[i][j]])
    # print(live)
    # print(info)


    for i in range(k):
        for power in range(10, 0, -1):  # 생명력의 범위는 1~10
            cur = live[power]
            new = []
            old = []
            for s in range(len(cur)-1, -1, -1):  # 파이썬 인덱스 감소 for문
                cur[s][2] -= 1
                x, y, z = cur[s]
                if z == -1:
                    for b in range(4):
                        nx, ny = (x+dx[b])%(n+k), (y+dy[b])%(m+k)  # 이동
                        if not info[nx][ny]:
                            info[nx][ny] = power
                            new.append([nx, ny, power])
                if z == -power:
                    old.append(s)
            for idx in old:
                cur.pop(idx)  # 비활성 셀 삭제
            cur += new  # 리스트 연결 가능

    answer = 0
    for i in range(1, 11):
        answer += len(live[i])

    print("#{} {}".format(tc, answer))

