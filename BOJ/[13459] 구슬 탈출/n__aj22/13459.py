import copy
ispossible = False
def changeposition(ry, rx, by, bx, direction, arr):
    if(direction == 0):
        #위
        if(ry<=by):
            for i in range(ry-1, -1, -1):
                if(arr[i][rx] == 'O'):
                    arr[ry][rx] = '.'
                    ry = i
                    break
                if(arr[i][rx]!='.'):
                    arr[i+1][rx] = 'R'
                    if(i+1 != ry):
                        arr[ry][rx] = '.'
                    ry = i + 1
                    break
                
            for i in range(by-1, -1, -1):
                if(arr[i][bx] == 'O'):
                    arr[by][bx] = '.'
                    by = i
                    break
                if(arr[i][bx]!='.'):
                    arr[i+1][bx] = 'B'
                    if(i+1!=by):
                        arr[by][bx] = '.'
                    by = i + 1
                    break
        else:
            for i in range(by-1, -1, -1):
                if(arr[i][bx] == 'O'):
                    arr[by][bx] = '.'
                    by = i
                    break
                if(arr[i][bx]!='.'):
                    arr[i+1][bx] = 'B'
                    if(i+1 != by):
                        arr[by][bx] = '.'
                    by = i + 1
                    break
            for i in range(ry-1, -1, -1):
                if(arr[i][rx] == 'O'):
                    arr[ry][rx] = '.'
                    ry = i
                    break
                if(arr[i][rx]!='.'):
                    arr[i+1][rx] = 'R'
                    if(i+1 != ry):
                        arr[ry][rx] = '.'
                    ry = i + 1
                    break
    elif(direction == 1):
        #오
        if(rx>=bx):
            for i in range(rx+1, M):
                if(arr[ry][i] == 'O'):
                    arr[ry][rx] = '.'
                    rx = i
                    break
                if(arr[ry][i]!='.'):
                    arr[ry][i-1] = 'R'
                    if(rx != i-1):
                        arr[ry][rx] = '.'
                    rx = i - 1
                    break
            for i in range(bx+1, M):
                if(arr[by][i] == 'O'):
                    arr[by][bx] = '.'
                    bx = i
                    break
                if(arr[by][i]!='.'):
                    arr[by][i-1] = 'B'
                    if(bx != i-1):
                        arr[by][bx] = '.'
                    bx = i - 1
                    break
        else:
            for i in range(bx+1, M):
                if(arr[by][i] == 'O'):
                    arr[by][bx] = '.'
                    bx = i
                    break
                if(arr[by][i]!='.'):
                    arr[by][i-1] = 'B'
                    if(i-1 != bx):
                        arr[by][bx] = '.'
                    bx = i - 1
                    break
            for i in range(rx+1, M):
                if(arr[ry][i] == 'O'):
                    arr[ry][rx] = '.'
                    rx = i
                    break
                if(arr[ry][i]!='.'):
                    arr[ry][i-1] = 'R'
                    if(i-1!=rx):
                        arr[ry][rx] = '.'
                    rx = i - 1
                    break
    elif(direction == 2):
        #아
        if(ry>=by):
            for i in range(ry+1, M):
                if(arr[i][rx] == 'O'):
                    arr[ry][rx] = '.'
                    ry = i
                    break
                if(arr[i][rx]!='.'):
                    arr[i-1][rx] = 'R'
                    if(i-1 != ry):
                        arr[ry][rx] = '.'
                    ry = i - 1
                    break
            for i in range(by+1, M):
                if(arr[i][bx] == 'O'):
                    arr[by][bx] = '.'
                    by = i
                    break
                if(arr[i][bx]!='.'):
                    arr[i-1][bx] = 'B'
                    if(i-1 != by):
                        arr[by][bx] = '.'
                    by = i - 1
                    break
        else:
            for i in range(by+1, M):
                if(arr[i][bx] == 'O'):
                    arr[by][bx] = '.'
                    by = i
                    break
                if(arr[i][bx]!='.'):
                    arr[i-1][bx] = 'B'
                    if(i-1 != by):
                        arr[by][bx] = '.'
                    by = i - 1
                    break
            for i in range(ry+1, M):
                if(arr[i][rx] == 'O'):
                    arr[rx][rx] = '.'
                    ry = i
                    break
                if(arr[i][rx]!='.'):
                    arr[i-1][rx] = 'R'
                    if(i-1!=ry):
                        arr[ry][rx] = '.'
                    ry = i - 1
                    break
    else:
        #왼
        if(rx<=bx):
            for i in range(rx-1, -1, -1):
                if(arr[ry][i] == 'O'):
                    arr[ry][rx] = '.'
                    rx = i
                    break
                if(arr[ry][i]!='.'):
                    arr[ry][i+1] = 'R'
                    if(i+1 != rx):
                        arr[ry][rx] = '.'
                    rx = i + 1
                    break
            for i in range(bx-1,-1, -1):
                if(arr[by][i] == 'O'):
                    arr[by][bx] = '.'
                    bx = i
                    break
                if(arr[by][i]!='.'):
                    arr[by][i+1] = 'B'
                    if(i+1 != bx):
                        arr[by][bx] = '.'
                    bx = i + 1
                    break
        else:
            for i in range(bx-1,-1, -1):
                if(arr[by][i] == 'O'):
                    arr[by][bx] = '.'
                    bx = i
                    break
                if(arr[by][i]!='.'):
                    arr[by][i+1] = 'B'
                    if(i+1 != bx):
                        arr[by][bx] = '.'
                    bx = i + 1
                    break
            for i in range(rx-1, -1, -1):
                if(arr[ry][i] == 'O'):
                    arr[ry][rx] = '.'
                    rx = i
                    break
                if(arr[ry][i]!='.'):
                    arr[ry][i+1] = 'R'
                    if(i+1 != rx):
                        arr[ry][rx] = '.'
                    rx = i + 1
                    break
    return ry, rx, by, bx, arr
def move(ry, rx, by, bx, arr, count):
    if(count>10 or (by== Ty and bx == Tx)):
        return
    if(ry == Ty and rx == Tx):
        global ispossible
        ispossible = True
        return
    
    for i in range(4):
        temp = copy.deepcopy(arr)
        newry, newrx, newby, newbx, arr = changeposition(ry, rx, by, bx, i, arr)
        if(newry != ry or newrx != rx or newby != by or newbx != bx):
            move(newry, newrx, newby, newbx, arr, count+1)
        if(ispossible):
            break
        arr = temp 

if __name__ == "__main__":
    N, M = map(int, input().split())
    map_arr = []
    Ry, Rx, By, Bx, Ty, Tx = 0, 0, 0, 0, 0, 0

    for i in range(N):
        new_arr = list(input())
        for j in range(M):
            if(new_arr[j] == 'R'):
                Ry, Rx = i, j
            if(new_arr[j] == 'B'):
                By, Bx = i, j
            if(new_arr[j] == 'O'):
                Ty, Tx = i, j
        map_arr.append(new_arr)
    move(Ry, Rx, By, Bx, map_arr, 0)
    if(ispossible):
        print(1)
    else:
        print(0)


        