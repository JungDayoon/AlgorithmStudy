def solution(triangle):
    # answer = 0
    prevAnswer = [0]

    for i in range(len(triangle)):
        nowLine = triangle[i]
        nowAnswer = [0 for _ in range(len(nowLine))]
        for ni in range(len(nowLine)):
            if ni == 0:
                nowAnswer[ni] = prevAnswer[ni] + nowLine[ni]
            elif ni == len(nowLine)-1:
                nowAnswer[ni] = prevAnswer[len(prevAnswer)-1] + nowLine[ni]
            else:
                nowAnswer[ni] = max(prevAnswer[ni-1], prevAnswer[ni]) + nowLine[ni]
        prevAnswer = nowAnswer[:]

    # print(prevAnswer)
    return max(prevAnswer)


solution([[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]])