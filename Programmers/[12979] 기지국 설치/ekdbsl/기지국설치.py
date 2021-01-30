def solution(n, stations, w):
    answer = 0
    base = 1
    dist = w * 2 + 1
    for station in stations:
        if station - w <= base:
            base = station + w + 1
            continue
        if ((station - w) - base) % dist != 0:
            answer += ((station - w) - base) // dist + 1
        else:
            answer += ((station - w) - base) // dist
        base = station + w + 1

    if base <= n:
        if (n + 1 - base) % dist != 0:
            answer += (n + 1 - base) // dist + 1
        else:
            answer += (n + 1 - base) // dist
    return answer


print(solution(11, [2, 4, 9], 1))
print(solution(16, [9], 2))
