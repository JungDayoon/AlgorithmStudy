from itertools import combinations
dx = [0, 0, -1, 1] #상하좌우
dy = [-1, 1, 0, 0]
def isin(y, x, N):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def check_possibility(arr, teachers, N):
    for t in teachers:
        ty, tx = t
        for i in range(4):
            cnt = 1
            while(True):
                next_ty, next_tx = ty+dy[i]*cnt, tx+dx[i]*cnt
                if(not isin(next_ty, next_tx, N) or arr[next_ty][next_tx] == 'O'):
                    break
                if(arr[next_ty][next_tx] == 'S'):
                    return False
                cnt+=1

    return True


if __name__ == "__main__":
    N = int(input())
    arr = []
    teachers = []
    empty = []
    for i in range(N):
        newlist = list(input().split())
        arr.append(newlist)
        for j in range(N):
            if newlist[j] == 'T':
                teachers.append([i, j])
            elif newlist[j] == 'X':
                empty.append([i, j])
    
    comb = list(combinations(empty, 3))

    flag = "NO"
    for c in comb:
        for i in c:
            y, x = i
            arr[y][x] = 'O'
        
        if(check_possibility(arr, teachers, N)):
            flag = "YES"
            break

        for i in c:
            y, x = i
            arr[y][x] = 'X'
    
    print(flag)