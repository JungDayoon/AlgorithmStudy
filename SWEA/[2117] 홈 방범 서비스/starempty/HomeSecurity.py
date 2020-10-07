def main():
    z = int(input())

    for tc in range(1, z+1):
        n, m = map(int, input().split())
        home = []
        city = []
        for i in range(n):
            tmp = list(map(int, input().split()))
            for j in range(n):
                if tmp[j] == 1:
                    home.append([i,j])
            city.append(tmp)

        k = 1
        bigset = []
        while(k < n+2):
            big = 0
            for i in range(len(city)):
                for j in range(len(city)):
                    cnt = 0
                    for c in range(len(home)):
                        if abs(home[c][0]-i)+abs(home[c][1]-j) < k:
                            cnt += 1
                    if big < cnt:
                        big = cnt
            bigset.append(big)
            k += 1
        print(bigset)

        index = 1
        answer = 0
        for sb in range(len(bigset)):
            rage = (index-1)*(index-1)+index*index
            tmp = m*bigset[sb] - rage
            print(tmp, end=" ")
            if tmp >= 0:
                if answer < sb:
                    answer = sb
            index += 1

        print("#{} {}".format(tc, bigset[answer]))




main()