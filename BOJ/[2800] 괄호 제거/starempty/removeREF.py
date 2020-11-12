from itertools import combinations

string = [*input().strip()]
p, idx = [],[]
for i, v in enumerate(string):
    if v == '(':
        string[i] = ''
        p += [i]
    elif v == ')':
        string[i] = ''  # 비워두고 채웠다 그냥 뒀다 하려고 => 이 부분을 빼고
        idx.append([p.pop(), i])  # 괄호 짝 추가해주기
#print(idx)
answer = set()
for i in range(len(idx)):
    for j in combinations(idx, i):  # idx 중 i개만 뽑는 경우의 수(따라서 0번째는 항상 모든 괄호를 제거한 string)
        #print(j)
        P = string[:]
        for x, y in j:
            P[x] = '(' # => 이부분을 ''로 바꾸면 틀린다.(연구해볼것..)
            P[y] = ')'
        #print(P)
        answer.add(''.join(P)) # 띄어쓰기를 모두 없애주고(join) add to answer

for i in sorted(answer):  # sorted는 아스키기준으로 작동하므로 괄호도 고려하여 정렬한다.
    print(i)