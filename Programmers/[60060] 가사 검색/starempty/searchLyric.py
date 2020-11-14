from bisect import bisect_left, bisect_right
from collections import defaultdict


def count_by_lange(string, right, left):
    #print(string, end='!'), 길이에 맞게 string을 보내고 접두/미사와 같은 단어가 얼마나 있는지 확인한다.
    return bisect_right(string, left) - bisect_left(string, right)

def solution(words, queries):
    answer = []
    normal = defaultdict(list)
    reverse = defaultdict(list)

    for word in words:
        normal[len(word)].append(word)
        reverse[len(word)].append(word[::-1])  # 거꾸로 저장하는 방법 [::-1]

    for i in normal.values():
        i.sort()
    #print(normal)
    for i in reverse.values():
        i.sort()
    #print(reverse)

    for query in queries:
        if query[0] == '?':
            tmp = reverse[len(query)]
            right, left = query[::-1].replace('?','a'),query[::-1].replace('?','z')
            #print(right, left, end=' post')
        else:
            tmp = normal[len(query)]
            right, left = query.replace("?", "a"), query.replace("?", "z")
            #print(right, left, end=' pre')
        answer.append(count_by_lange(tmp, right, left))

    print(answer)
    return answer


solution(["frodo", "front", "frost", "frozen", "frame", "kakao"], ["fro??", "????o", "fr???", "fro???", "pro?"])