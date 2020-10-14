from itertools import combinations

##### 처음에 백트래킹으로 구현했던 부분 #####
# def solution(prev, Str):
#     for i in range(prev, len(Str)):
#         tmpStr = Str[:]
#         if Str[i] == '(':
#             stack.append(i)
#         elif Str[i] == ')':
#             pair = stack.pop()
#             Str.pop(i)
#             Str.pop(pair)
#             solution(i-1, Str)
#             stack.append(pair)
#             Str = tmpStr[:]
#     List.append(''.join(Str))

def makeComb(now, goal, prev, set):
    if now == goal:
        comb.append(set[:])
        return
    for i in range(prev+1, len(List)):
        set.append(List[i])
        makeComb(now+1, goal, i, set)
        set.pop()


def makePair():
    for i in range(len(calculStr)):
        if calculStr[i] == '(':
            stack.append(i)
        elif calculStr[i] == ')':
            List.append([stack.pop(), i])

calculStr = list(str(input()))

stack = []
result = set()
List = []
# removeList = []
# solution(0, [])
makePair()
# print(List)

for i in range(1, len(List) + 1):
    comb = []
    makeComb(0, i, -1, [])
    # comb = list(combinations(List, i))
    for c in comb:
        c = sum(c, [])
        c = sorted(c)
        newStr = calculStr[:]
        while c:
            newStr.pop(c.pop(0))
            c = list(map(lambda t: int(t)-1, c))

        result.add(''.join(newStr))

result = sorted(list(result))
for r in result:
    print(r)
