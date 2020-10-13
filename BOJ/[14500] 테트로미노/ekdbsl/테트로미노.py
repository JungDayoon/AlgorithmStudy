def isIn(y, x):
    if 0 <= x < M and 0 <= y < N:
        return True
    return False


Tetromino = [[[0, 0], [0, 1], [0, 2], [0, 3]], [[0, 0], [1, 0], [2, 0], [3, 0]], [[0, 0], [0, 1], [1, 0], [1, 1]],
             [[0, 0], [1, 0], [2, 0], [2, 1]], [[2, 0], [0, 1], [1, 1], [2, 1]], [[0, 0], [0, 1], [0, 2], [1, 0]],
             [[0, 0], [0, 1], [0, 2], [1, 2]], [[0, 0], [0, 1], [1, 0], [2, 0]], [[0, 0], [0, 1], [1, 1], [2, 1]],
             [[0, 0], [1, 0], [1, 1], [1, 2]], [[1, 0], [1, 1], [1, 2], [0, 2]], [[0, 0], [1, 0], [1, 1], [2, 1]],
             [[0, 1], [1, 1], [1, 0], [2, 0]], [[0, 0], [0, 1], [1, 1], [1, 2]], [[1, 0], [0, 1], [1, 1], [0, 2]],
             [[0, 0], [0, 1], [0, 2], [1, 1]], [[0, 1], [1, 0], [1, 1], [1, 2]], [[0, 0], [1, 0], [2, 0], [1, 1]],
             [[0, 1], [1, 1], [2, 1], [1, 0]]]
N, M = map(int, input().split())
scoreMap = [[int(x) for x in input().split()] for _ in range(N)]
maxScore = 0

for tet in Tetromino:
    for i in range(N):
        for j in range(M):
            score = 0
            flag = True
            for p in tet:
                ny = i + p[0]
                nx = j + p[1]

                if isIn(ny, nx):
                    score += scoreMap[ny][nx]
                else:
                    flag = False
                    break
            if flag:
                maxScore = max(maxScore, score)
print(maxScore)