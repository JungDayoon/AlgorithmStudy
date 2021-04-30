answer = 0

def backtracking(p_index, position_arr):
    if(p_index == 11):
        global answer
        answer = max(answer, sum(position_arr))
        return
    
    now_player = arr[p_index]
    for i in range(11):
        if(now_player[i]!=0 and position_arr[i] == 0):
            position_arr[i] = now_player[i]
            backtracking(p_index+1, position_arr)
            position_arr[i] = 0
    return

if __name__ == "__main__":
    T = int(input())
    for t in range(T):
        answer = 0
        position = [0]*11
        arr = [list(map(int, input().split())) for _ in range(11)]
        backtracking(0, position)
        print(answer)