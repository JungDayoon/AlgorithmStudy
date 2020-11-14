# 55점

def solution(words, queries):
    answer = []
    for i in queries:
        cnt = 0
        length = len(i)
        if i[0] == "?":
            i = i[::-1]  #문자열 뒤집는 방법
            postfix = i.split("?")[0][::-1]  # ?이후 문자 뒤집어서 저장
            for j in words:
                if len(postfix) == 0 and len(j) == length:
                    cnt += 1
                elif j.endswith(postfix) and len(j) == length:
                    cnt += 1
            answer.append(cnt)
        else:
            prefix = i.split("?")[0]
            for j in words:
                if len(prefix) == 0 and len(j) == length:
                    cnt += 1
                elif j.startswith(prefix) and len(j) == length:
                    cnt += 1
            answer.append(cnt)

    return answer

solution(["frodo", "front", "frost", "frozen", "frame", "kakao"], ["fro??", "????o", "fr???", "fro???", "pro?"])