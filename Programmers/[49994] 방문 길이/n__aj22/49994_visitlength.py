dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
def solution(dirs):
    arrlist = list(dirs)
    answer = len(visit(arrlist))
    return answer

def direction(character):
    if character == 'U':
        return 0
    elif character == 'R':
        return 1
    elif character == 'D':
        return 2
    else:
        return 3
def find(sx, sy, nx, ny):
    if(sx == nx):
        if(sy<ny):
            return str(sx)+" "+str(sy)+" "+str(nx)+" "+str(ny)
        else:
            return str(nx)+" "+str(ny)+" "+str(sx)+" "+str(sy)
    elif(sy == ny):
        if(sx<nx):
            return str(sx)+" "+str(sy)+" "+str(nx)+" "+str(ny)
        else:
            return str(nx)+" "+str(ny)+" "+str(sx)+" "+str(sy)

def visit(arr):
    startx = 0
    starty = 0
    list_of_road = {}
    for i in arr:
        d = direction(i)
        nextx = startx + dx[d]
        nexty = starty + dy[d]
        if(isin(nextx, nexty)==False):
            continue
        str_road = find(startx, starty, nextx, nexty)
        if(str_road in list_of_road.keys()):
            list_of_road[str_road]+=1
        else:
            list_of_road[str_road]=1
        startx = nextx
        starty = nexty
    return list_of_road
def isin(x, y):
    if(-5<=x<=5 and -5<=y<=5):
        return True
    else:
        return False

if __name__ == "__main__":
    print(solution("LULLLLLLU"))





