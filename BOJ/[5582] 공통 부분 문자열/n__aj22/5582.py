if __name__ == "__main__":
    str1 = list(input())
    str2 = list(input())
    
    M, N = len(str1), len(str2)

    arr = [[0]*N for _ in range(M)]
    answer = 0
    for i in range(M):
        for j in range(N):
            if(str1[i] == str2[j]):
                if(i == 0 or j == 0):
                    arr[i][j] = 1
                else:
                    arr[i][j] = arr[i-1][j-1]+1
        answer = max(answer, max(arr[i]))
    print(answer)
    