import sys
from itertools import combinations


def calculate(first, second, stair):
    st = sorted(first)
    nd = sorted(second)
    time1 = 0
    time2 = 0
    for i in range(len(st)):
        if i < 3:
            time1 = st[i]
        else:
            time1 = max(st[i], st[i-3]+stair[0])
    for j in range(len(nd)):
        if j < 3:
            time2 = nd[j]
        else:
            time2 = max(nd[j], nd[j-3]+stair[1])

    return max(time1, time2)

def arrange(first, person, stair):
    second = []
    for i in range(len(person)):
        if i in first:
            continue
        else:
            second.append(person[i][1] + stair[1])
    return second

def main():
    sys.stdin = open("input.txt", "r")
    num = int(input())

    for tc in range(1, num+1):
        n = int(input())
        array = []
        for i in range(n):
            tmp = list(map(int, input().split()))
            array.append(tmp)
        person = []
        stair = []
        row = []
        col = []

        for i in range(n):
            for j in range(n):
                if array[i][j] > 1:
                    stair.append(array[i][j])
                    row.append(i)
                    col.append(j)

        for i in range(n):
            for j in range(n):
                if array[i][j] == 1:
                    st = abs(i-row[0])+abs(j-col[0])+1
                    nd = abs(i-row[1])+abs(j-col[1])+1
                    person.append([st,nd])

        answer = []
        for i in range(n):
            choose = list(combinations(range(0,len(person)), i))
            for j in choose:
                index = []
                first = []
                for k in j:
                    first.append(person[k][0]+stair[0])
                    index.append(k)
                second = arrange(index, person, stair)
                answer.append(calculate(first, second, stair))
        print("#{} {}".format(tc, min(answer)))




main()