def rotate(old_str):
    new_str = ""

    new_str += old_str[len(old_str) - 1]
    for i in range(0, len(old_str) - 1):
        new_str += old_str[i]

    return new_str


T = int(input())
for test_case in range(1, T + 1):
    N, K = map(int, input().split())
    TreasureStr = input()
    answerList = []
    splitTreasure = []
    rotate_num = int(N / 4)

    for r in range(0, rotate_num):
        if r != 0:
            TreasureStr = rotate(TreasureStr)
        for i in range(0, N - (rotate_num - 1), rotate_num):
            splitTreasure.append(TreasureStr[i:i + rotate_num])

    resultArr = list(set(splitTreasure))
    resultArr.sort(reverse=True)
    result = int(resultArr[K - 1], 16)
    print("#{} {}".format(test_case, result))
