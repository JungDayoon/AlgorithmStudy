import sys
info = []

def make_check(d, w, k):
    flag = True
    for i in range(w):
        cmp = info[0][i]
        tmp = 1
        f = 0
        for j in range(1, d):
            if cmp == info[j][i]:
                tmp += 1
            else:
                tmp = 1
            cmp = info[j][i]
            if tmp >= k:
                f = 1
                break
        if f == 0:
            flag = False
            break
    #     check.append(max(set))
    # flag = True
    # for i in check:
    #     if i < k:
    #         flag = False
    #         break
    return flag

sys.stdin = open("input.txt", "r")
z = int(input())

for tc in range(1, z+1):
    info = []
    d, w, k = map(int, input().split())
    ans = d
    for i in range(d):
        tmp = list(map(int, input().split()))
        info.append(tmp)

    def injection(cnt, index):  # 한 줄씩 넣어보고 k_search 0일 때 몇 줄 주사했는지 반환
        global ans
        # print(cnt, index)
        # for i in range(d):
        #     for j in range(w):
        #         print(info[i][j], end='')
        #     print(" ")
        if cnt >= ans:
             return
        if make_check(d,w,k):
            ans = min(ans, cnt)
            return
        if index == d or cnt > k: return
        tmp = []
        for i in range(w):
            tmp.append(info[index][i])
        #print(tmp)

        injection(cnt, index+1)
        for s in range(w):
            info[index][s] = 1
        injection(cnt+1, index+1)

        for b in range(w):
            info[index][b] = 0
        injection(cnt+1, index+1)
        for j in range(w):
            info[index][j] = tmp[j]


    if make_check(d,w,k) or k==1:
        answer = 0
    else:
        injection(0,0)
        answer = ans

    print("#{} {}".format(tc, answer))

