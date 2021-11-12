from itertools import combinations

expression = input()
bracket = []
stack = []
for i in range(len(expression)):
    if expression[i]=="(":
        stack.append(i)
    elif expression[i]==")":
        pre = stack.pop()
        bracket.append([pre,i])
        
combi = []
for i in range(1,len(bracket)+1):
    combi+=list(combinations(bracket,i))

answer = []
for com in combi:
    temp = []
    for c in com:
        temp+=c
    temp_str = ''
    for i in range(len(expression)):
        if i not in temp:
            temp_str+=expression[i]
    if temp_str not in answer:
        answer.append(temp_str)

answer.sort()
for i in answer:
    print(i)