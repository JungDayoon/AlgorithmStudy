flag = False
def check(arr):
    for length in range(1, len(arr)//2+1):
        if(arr[len(arr)-length:] == arr[len(arr)-length*2:len(arr)-length]):
            return False

    return True

def backtracking(arr):
    if not check(arr):
        return 
    if len(arr) == N:
        for i in range(N):
            print(arr[i], end = "")
        global flag
        flag = True
        return
    
    for i in range(1, 4):
        arr.append(i)
        backtracking(arr)
        if flag:
            return
        arr.pop(-1)
    return
if __name__ == "__main__":
    N = int(input())
    backtracking([])
