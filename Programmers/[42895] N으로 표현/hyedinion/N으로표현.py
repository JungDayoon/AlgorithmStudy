def solution(N, number):
    if N==number:
        return 1
    s = ['*', '/', '+', '-']
    DP = [[] for _ in range(9)]
    DP[1].append(N)
    for I in range(2,9):
        for K in range(1,int(I//2)+1):
            for J in range(4):
                for pre1 in DP[K]:
                    for pre2 in DP[I-K]:
                        temp1 = int(eval(str(pre2)+s[J]+str(pre1)))
                        temp2 = int(eval(str(pre1)+s[J]+str(pre2)))
                        if temp1==number:
                            return I
                        if temp1>0:
                            DP[I].append(temp1)
                        if temp2==number:
                            return I
                        if temp2>0:
                            DP[I].append(temp2)
        temp = int(str(N)*I)
        if temp ==number:
            return I
        else:
            DP[I].append(temp)
        DP[I] = list(set(DP[I]))
        
    return -1

print(solution(5,127))