N = int(input())
video = [[int(x) for x in input()]for y in range(N)]
outStr = ""

def isSame(x_start, x_end, y_start, y_end):
    compare = video[y_start][x_start]

    for i in range(y_start, y_end):
        for j in range(x_start, x_end):
            if(compare != video[i][j]):
                return False
    return True

def quadTree(x_start, x_end, y_start, y_end):
    global outStr
    if(isSame(x_start, x_end, y_start, y_end)):
        outStr += str(video[y_start][x_start])
    else:
        outStr += "("
        x_mid = (x_start+x_end)//2
        y_mid = (y_start+y_end)//2
        quadTree(x_start, x_mid, y_start, y_mid)
        quadTree(x_mid, x_end, y_start, y_mid)
        quadTree(x_start, x_mid, y_mid, y_end)
        quadTree(x_mid, x_end, y_mid, y_end)
        outStr += ")"

quadTree(0, N, 0, N)
print(outStr)