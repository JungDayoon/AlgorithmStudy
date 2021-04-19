user_id = ["frodo", "fradi", "crodo", "abc123", "frodoc"]
banned_id = ["fr*d*", "abc1**"]

import copy
answerList = []
def find_id(length,b,lis):
    answerList = []
    for l in lis:
        check = True
        for i in range(length):
            if b[i]=='*':
                continue
            if b[i]!=l[i]:
                check = False
                break
        if check:
            answerList.append(l) 
    return answerList

def find_comb(idx,banList,dic):
    global answerList
    if idx == len(banList):
        if len(dic.keys())==idx:
            if dic not in answerList:
                answerList.append(copy.deepcopy(dic))
        return
    for l in banList[idx]:
        if l in dic.keys():
            dic[l]+=1
        else:
            dic[l] = 1
        find_comb(idx+1,banList,dic)
        if dic[l]>1:
            dic[l]-=1
        else:
            del dic[l]
    return

def solution(user_id, banned_id):
    userDict={}
    # length를 key로 user 딕셔너리 만들기
    for s in user_id:
        length = len(s)
        if length not in userDict.keys():
            userDict[length] = [s]
        else:
            userDict[length].append(s)
    
    banList = [[] for _ in range(len(banned_id))]
    for i,b in enumerate(banned_id):
        length = len(b)
        banList[i] = find_id(length,b,userDict[length])
    find_comb(0,banList,{})
    return len(answerList)

print(solution(user_id,banned_id))