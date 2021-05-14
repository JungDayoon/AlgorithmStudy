#이분탐색(정확성 성공, 효율성 실패)
def calculate(land, P, Q, N, height):
    total = 0
    for i in range(N):
        for j in range(N):
            if land[i][j]>height:
                total += ((land[i][j] - height)*Q)
            if land[i][j]<height:
                total += ((height - land[i][j])*P)
    return total
def solution(land, P, Q):
    answer = -1
    N = len(land)
    max_height, min_height = land[0][0], land[0][0]

    for i in range(N):
        for j in range(N):
            if(land[i][j]>max_height):
                max_height = land[i][j]
            if(land[i][j]<min_height):
                min_height = land[i][j]
    
    while(min_height<=max_height):
        mid_height = (max_height+min_height)//2 
        total = calculate(land, P, Q, N, mid_height)
        if answer == -1:
            answer = total
        else:
            answer = min(answer, total)    
        total2 = calculate(land, P, Q, N, mid_height-1)
        if total<total2:
            min_height = mid_height+1
        elif total > total2:
            max_height = mid_height-1
        else:
            break
        
    return answer
if __name__ == "__main__":
    land = [[[1, 2], [2, 3]],[[4, 4, 3], [3, 2, 2], [2, 1, 0]]]
    P = [3, 5]
    Q = [2, 3]

    for i in range(2):
        print(solution(land[i], P[i], Q[i]))