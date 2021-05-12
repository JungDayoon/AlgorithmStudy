def solution(A, B):
    answer = 0
    A.sort()
    B.sort()
    A_start, B_start, card_num = 0, 0, len(A)
    
    while(True):
        if A_start == card_num or B_start == card_num:
            break
        if(A[A_start] < B[B_start]):
            answer+=1
            A_start+=1
            B_start+=1
            
        else:
            B_start+=1
            

    return answer

if __name__ == "__main__":
    A = [[5,1,3,7],[2,2,2,2]]
    B = [[2,2,6,8],[1,1,1,1]]

    for i in range(2):
        print(solution(A[i],B[i]))