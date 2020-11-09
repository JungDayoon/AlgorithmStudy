def check():
    for i in range(n):
        k = i
        for j in range(h):
            if col[j][k]:
                k += 1
            elif col[j][k-1]:
                k -= 1
        if k != i:
            return False
    return True


def dfs(cnt, x, y):
    global answer
    if check():  # 이동했을 때 본인의 출발값과 도착값이 같은지 확인하는 함수
        answer = min(answer, cnt)
        return
    elif cnt == 3 or answer <= cnt:
        return
    for i in range(x, h):
        ny = y if i == x else 0  # x,y 위치에서 이동하므로 다음 줄부터는 0부터 확인한다.
        for j in range(ny, n-1):
            if not col[i][j] and not col[i][j+1]:  # 연결되지 않았다면,
                col[i][j] = 1
                dfs(cnt+1, i, j+2)
                col[i][j] = 0


if __name__ == '__main__':
    n, m, h = map(int, input().split())
    col = list([0]*n for _ in range(h))

    for i in range(m):
        a, b = map(int, input().split())
        col[a-1][b-1] = 1
    #print(col)

    answer = 4
    dfs(0,0,0)
    print(answer if answer < 4 else -1)
    #for i in range(h):
