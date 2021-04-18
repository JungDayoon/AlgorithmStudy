n = 9
path = [[0, 1], [0, 3], [0, 7], [8, 1], [3, 6], [1, 2], [4, 7], [7, 5]]
order = [[8,5],[6,7],[4,1]]

pathDict = dict()
change = True

def find_path(queue, checkList, ordict):
    global change
    change = False
    failList = []
    while len(queue)!=0:
        n = queue.pop()
        if checkList[n]== False:
            #조건이 만족됐는지 확인
            #만족 안됐으면 failList에 담아줌
            if n in ordict.keys():
                if checkList[ordict[n]] == False:
                    failList.append(n)
                    continue
            #조건이 만족 되었을 때
            checkList[n]=True
            change = True
                
        #자식이 있을 때
        if n in pathDict.keys():
            #자식방문
            for child in pathDict[n]:
                #자식이 조건이 있을경우
                if child in ordict.keys():
                    if checkList[ordict[child]]==False:
                        failList.append(child)
                        continue
                #조건이 없거나 방문가능
                if checkList[child]==False:
                    checkList[child]=True
                    change = True
                    queue.append(child)

    return failList


def solution(n, path, order):
    global pathDict
    #조건을 담고있는 order dictionary
    ordict = dict()
    for o in order:
        ordict[o[1]]=o[0]
    #방문여부 check
    checkList = dict()
    for i in range(n):
        checkList[i] = False
        
    #path adj dictionary만들기
    for p in path:
        if p[0] not in pathDict.keys():
            pathDict[p[0]] = [p[1]]
        else:
            pathDict[p[0]].append(p[1])
        if p[1] not in pathDict.keys():
            pathDict[p[1]] = [p[0]]
        else:
            pathDict[p[1]].append(p[0])
            
    #방문시작
    queue = [0]
    while change:
        queue = find_path(queue, checkList,ordict)
    
    #방문 성공인지 확인
    for i in range(n):
        if not checkList[i]:
            return False
        
    return True

print(solution(n,path,order))