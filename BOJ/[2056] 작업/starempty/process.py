def main():
    work = []
    node = []
    answer = []
    n = int(input())

    for i in range(n):
        answer.append(0)
        tmp = list(map(int, (input().split())))
        work.append(tmp[0])
        tmp2 = []
        for j in range(tmp[1]):
            tmp2.append(tmp[2+j])
        node.append(tmp2)

    for i in range(len(node)):
        if node[i]:
            tmp_ans = []
            for j in range(len(node[i])):
                parent = node[i][j]-1
                tmp_ans.append(answer[parent])

            answer[i] = max(tmp_ans) +work[i]
        else:
            answer[i] = work[i]

    print(max(answer))

main()