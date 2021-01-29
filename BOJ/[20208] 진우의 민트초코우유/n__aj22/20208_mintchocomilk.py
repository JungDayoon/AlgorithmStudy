max_milk = 0
def cal_dis(y1, x1, y2, x2):
    return abs(y1-y2)+abs(x1-x2)
def backtracking(health, y, x, milk_num):
    global max_milk
    if(milk_num>max_milk):
        if(health >= cal_dis(y, x, home_y, home_x)):
            max_milk = max(max_milk, milk_num)
    if(health<=0):
        return
    for i in range(len(milk_arr)):
        if(visited[i] == False):
            nexty = milk_arr[i][0]
            nextx = milk_arr[i][1]
            minus_health = cal_dis(y,x,nexty,nextx)
            if(health-minus_health>=0):
                visited[i] = True
                backtracking(health-minus_health+H, nexty, nextx, milk_num+1)
                visited[i] = False
        
if __name__ == "__main__":
    N, M, H = map(int, input().split())
    milk_arr = []
    for i in range(N):
        new_arr = list(map(int, input().split()))
        for j in range(N):
            if new_arr[j] == 1:
                home_y = i
                home_x = j
            if new_arr[j] == 2:
                milk_arr.append([i, j])
    visited = [False]*len(milk_arr)
    backtracking(M, home_y, home_x, 0)
    print(max_milk)