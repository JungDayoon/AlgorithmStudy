def power(arr, num):
    ans = [[0]*N for _ in range(N)]
    for i in range(N):
        ans[i][i] = 1

    while(num>0):
        if(num%2==1):
            ans = multi(ans, arr)
            num = num-1
        arr = multi(arr, arr)
        num = num/2
    
    return ans

def multi(arr1, arr2):
    ans = [[0]*N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            sum_of_num = 0
            for k in range(N):
                sum_of_num+=(arr1[i][k]*arr2[k][j])
            ans[i][j] = sum_of_num%1000
    return ans

def print_arr(arr):
    for line in arr:
        for num in line:
            print(str(num), end=" ")
        print()

if __name__ == "__main__":
    N, B = map(int, input().split())

    matrix = []
    for i in range(N):
        matrix.append(list(map(int, input().split())))
    
    print_arr(power(matrix, B))