Flag = 0
film = []
K = 0


def copyrow(arr, type):
    for i in range(len(arr)):
        arr[i] = type


def chk_medi(goal, num, prev):
    global Flag
    if goal == num:
        if check_byk():
            Flag = 1
        return

    for i in range(prev, len(film)):
        arr = []
        for a in range(len(film[0])):  # 원래 배열 저장
            arr.append(film[i][a])

        copyrow(film[i], 0)
        chk_medi(goal, num + 1, i+1)
        if Flag == 1: return

        copyrow(film[i], 1)
        chk_medi(goal, num + 1, i+1)
        if Flag == 1: return
        for a in range(len(film[0])):  # film 원래대로
            film[i][a] = arr[a]


def check_byk():
    global K
    # print("가로" + str(len(film[0])))
    # print("세로" + str(len(film)))

    for i in range(0, len(film[0])):
        # print("i: "+str(i))
        same = 1
        f = 0
        for j in range(1, len(film)):
            # print(j, end='')
            if film[j][i] == film[j - 1][i]:
                same += 1
            else:
                same = 1

            if same >= K:
                f = 1
                break
        if f == 0:
            return False
    return True


T = int(input())
Flag = 0
for t in range(0, T):
    Flag = 0
    film = []
    D, W, K = map(int, input().split())
    result = []
    for i in range(D):
        film.append(list(map(int, input().split())))

    if check_byk():
        print("#" + str(t + 1) + " 0")
        continue

    if K == 1:
        print("#" + str(t + 1) + " 0")
        continue


    for l_k in range(1, K):
        chk_medi(l_k, 0, 0)
        if Flag == 1:
            break

    if Flag == 0:
        print("#" + str(t + 1) + " " + str(K))
    else:
        print("#" + str(t + 1) + " " + str(l_k))
    


