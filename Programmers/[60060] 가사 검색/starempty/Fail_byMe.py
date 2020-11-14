def solution(words, queries):
    answer = []
    for i in queries:
        cnt = 0
        for j in words:
            flag = 0
            if len(i) != len(j):
                flag = 1
                break
            else:
                for k in range(len(i)):
                    if i[k] == '?':
                        continue
                    elif i[k] == j[k]:
                        continue
                    else:
                        flag = 1
                        break
            if flag == 0:
                cnt += 1
        answer.append(cnt)
    print(answer)
    return answer

solution(["frodo", "front", "frost", "frozen", "frame", "kakao"], ["fro??", "????o", "fr???", "fro???", "pro?"])