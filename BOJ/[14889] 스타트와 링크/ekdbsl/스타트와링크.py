def makeComb(now, goal, prev, tmp):
    if now == goal:
        teamList.append(tmp[:])
        return
    for i in range(prev+1, N):
        tmp.append(i)
        makeComb(now + 1, goal, i, tmp)
        tmp.pop()



def makePermu(now, goal, tmp, list, visited, arr):
    if now == goal:
        list.append(tmp[:])
        return
    for i in range(0, len(arr)):
        if not visited[i]:
            tmp[now] = arr[i]
            visited[i] = True
            makePermu(now + 1, goal, tmp, list, visited, arr)
            visited[i] = False


def getSkill(arr):
    skill = 0
    list = []
    visited = [False for _ in range(N//2)]
    makePermu(0, 2, [0, 0], list, visited, arr)
    # print(list)
    for l in list:
        skill += skillMap[l[0]][l[1]]
    return skill


N = int(input())
skillMap = [[int(x) for x in input().split()] for _ in range(N)]
player = [int(x) for x in range(N)]
minSkill = -1

teamList = []
makeComb(0, N // 2, -1, [])
# print(teamList)
length = len(teamList)
for team in teamList[:length//2]:
    # print(team)
    other_team = [x for x in player if x not in team]
    if minSkill == -1:
        minSkill = abs(getSkill(team) - getSkill(other_team))
    else:
        minSkill = min(minSkill, abs(getSkill(team) - getSkill(other_team)))

print(minSkill)