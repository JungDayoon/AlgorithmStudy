def solution(N, number):
    dp = ['dummy']

    for i in range(1,9): #cur dp
        tmp = {int(str(N)*i)}
        for j in range(1, i): #prev dp
            for first in dp[j]:
                for second in dp[-j]:
                    tmp.add(first+second)
                    tmp.add(first-second)
                    tmp.add(first*second)
                    if second != 0:
                        tmp.add(first//second)

        if number in tmp:
            return i
        dp.append(tmp)
    return -1

solution(1, 1121)