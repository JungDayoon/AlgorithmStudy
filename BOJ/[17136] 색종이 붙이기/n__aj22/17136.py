answer = -1
def isin(y, x):
    if 0<=y<10 and 0<=x<10:
        return True
    return False
def change(y, x, paper_length, visited):

    for n in range(y, y+paper_length):
        for m in range(x, x+paper_length):
            if(not isin(n, m) or arr[n][m] != 1 or visited[n][m]):
                return False

    for n in range(y, y+paper_length):
        for m in range(x, x+paper_length):
            visited[n][m] = True
    return True
def rechange(y, x, paper_length, visited):
    for n in range(y, y+paper_length):
        for m in range(x, x+paper_length):
            visited[n][m] = False
    return
def backtracking(visited, p_arr, leftnum, papern):
    if(leftnum == 0):
        global answer 
        if answer == -1:
            answer = papern
        else:
            answer = min(answer, papern)

        return
    flag = False
    for i in range(10):
        for j in range(10):
            if(not visited[i][j] and arr[i][j] == 1):
                for k in range(5, 0, -1):
                    if p_arr[k] == 5:
                        continue
                    check = change(i, j, k, visited)
                    if(not check):
                        continue
                    p_arr[k]+=1
                    backtracking(visited, p_arr, leftnum-k*k, papern+1)
                    p_arr[k]-=1
                    rechange(i, j, k, visited)
                return

if __name__ == "__main__":
    arr = []
    visited = [[False]*10 for _ in range(10)]
    check = [0, 0, 0, 0, 0, 0]
    square_num = 0
    for i in range(10):
        newlist = list(map(int, input().split()))
        arr.append(newlist)
        for j in range(10):
            if(newlist[j] == 1):
                square_num+=1
    backtracking(visited, check, square_num, 0)
    print(answer)
    

    