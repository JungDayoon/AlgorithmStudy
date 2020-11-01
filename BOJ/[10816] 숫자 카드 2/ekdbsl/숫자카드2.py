from sys import stdin

_ = stdin.readline()
Card = map(int, stdin.readline().split())
Dict = {}
_ = stdin.readline()
Compare = map(int, stdin.readline().split())

for card in Card:
    if card not in Dict:
        Dict[card] = 0
    Dict[card] += 1

result = []
for compare in Compare:
    if compare not in Dict:
        result.append(str(0))
    else:
        result.append(str(Dict[compare]))
print(' '.join(result))


### 잘못된 방법 ###
# outStr = ""
# for compare in Compare:
#     if compare not in Dict:
#         outStr += "0 "
#     else:
#         outStr += str(Dict[compare]) + " "
#     print(id(outStr))
#
# print(outStr)
