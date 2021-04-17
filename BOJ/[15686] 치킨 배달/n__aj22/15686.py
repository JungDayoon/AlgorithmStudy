import itertools
def find_min_distance(arr):
    dis_sum = 0
    for h in home:
        min_d = float('inf')
        hy, hx = h
        for c in arr:
            y, x = c
            min_d = min(min_d, abs(hy-y)+abs(hx-x))
        dis_sum+=min_d
    return dis_sum
    
if __name__ == "__main__":
    N, M = map(int, input().split())
    home, chicken = [], []
    for i in range(N):
        new_arr = list(map(int, input().split()))
        for j in range(N):
            if(new_arr[j] == 1):
                home.append([i, j])
            elif(new_arr[j] == 2):
                chicken.append([i, j])
    
    choose_chicken = list(itertools.combinations(chicken, M))
    answer = float('inf')
    for c in choose_chicken:
        answer = min(answer, find_min_distance(list(c)))
    print(answer)