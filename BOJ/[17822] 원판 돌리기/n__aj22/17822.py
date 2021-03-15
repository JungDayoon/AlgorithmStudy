import copy
def rotate(arr, num, direction):
    temp = [0]*M
    if(direction==1):
        num = M-num
    
    for i in range(M):
        temp[(i+num)%M] = arr[i]
    
    return temp

def delete_same_num(arr):
    flag = False
    delete_sum = 0
    delete_num = 0
    visited = [[False]*M for _ in range(N)]
    #1) 가로
    for i in range(N):
        for j in range(M):
            if(arr[i][j] == arr[i][(j+1)%M] and arr[i][j]!= -1 and arr[i][(j+1)%M]!=-1):
                visited[i][j], visited[i][(j+1)%M] = True, True
                flag = True


    #2) 세로
    for i in range(N-1):
        for j in range(M):
            if(arr[i][j] == arr[i+1][j] and arr[i][j]!= -1 and arr[i+1][j]!= -1):
                visited[i][j], visited[i+1][j] = True, True
                flag = True

    if(flag):
        for i in range(N):
            for j in range(M):
                if(visited[i][j]):
                    delete_sum+=arr[i][j]
                    delete_num+=1
                    arr[i][j] = -1
    return flag, delete_sum, delete_num, arr
def make_balance(arr, num, total):
    weight = total/num
    for i in range(N):
        for j in range(M):
            if(arr[i][j]!=-1):
                if(arr[i][j]>weight):
                    arr[i][j]-=1
                    total-=1
                elif(arr[i][j]<weight):
                    arr[i][j]+=1
                    total+=1

    return arr, total
if __name__ == "__main__":
    N, M, T = map(int, input().split())
    circle_list =[]
    total_sum, total_num = 0, N*M
    for i in range(N):
        circle_list.append(list(map(int, input().split())))
        total_sum+=sum(circle_list[i])
    
    for tc in range(T):
        x, d, k = map(int, input().split())
        for index in range(N):
            if((index+1)%x == 0):
                circle_list[index] = rotate(circle_list[index], k, d)

        check, delete_sum, delete_num, circle_list = delete_same_num(circle_list)

        if(check):
            total_sum-=delete_sum
            total_num-=delete_num
        else:#지워진 숫자가 없으면
            if(total_num!=0):
                circle_list, total_sum = make_balance(circle_list, total_num, total_sum)
        
    print(total_sum)