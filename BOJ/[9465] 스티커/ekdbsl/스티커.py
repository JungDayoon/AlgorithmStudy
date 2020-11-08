T = int(input())
for t_c in range(T):
    n = int(input())
    sticker = [[int(x) for x in input().split()]for _ in range(2)]
    sticker[0].insert(0,0)
    sticker[1].insert(0,0)
    answer = [[0 for _ in range(n+1)]for _ in range(3)]

    for i in range(1, n+1):
        answer[0][i] = max(answer[1][i-1] + sticker[0][i], answer[2][i-1] + sticker[0][i])
        answer[1][i] = max(answer[0][i-1] + sticker[1][i], answer[2][i-1] + sticker[1][i])
        answer[2][i] = max(answer[0][i-1], answer[1][i-1], answer[2][i-1])

    # print(answer)
    print(max(answer[0][n], answer[1][n], answer[2][n]))