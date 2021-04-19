s = '{{2},{2,1},{2,1,3},{2,1,3,4}}'

import re
def solution(s):
    #숫자 찾기
    lis = re.findall('\d+',s)
    numDict = {}
    for l in lis:
        if l not in numDict.keys():
            numDict[l] = 1
        else:
            numDict[l] +=1
    
    N = len(numDict.keys())
    answerlist = [0 for _ in range(N)]
    for k,v in numDict.items():
        answerlist[N-v] = int(k)
        
    return answerlist

print(solution(s))