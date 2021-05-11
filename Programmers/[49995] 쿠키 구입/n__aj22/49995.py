def solution(cookie):
    answer = -1

    sum_list = []
    for i in range(len(cookie)+1):
        sum_list.append(sum(cookie[0:i]))

    half_sum = sum_list[-1]//2

    for m in range(len(cookie)):
        for l in range(m+1):
            #l~m
            now_sum = sum_list[m+1]-sum_list[l]
            last_sum = sum_list[len(cookie)] - sum_list[m+1]
            if now_sum>last_sum: # 남은 쿠키 개수보다 현재 확인할 합계가 작으면 확인할 필요 없음
                continue
            # if now_sum>half_sum: #현재 확인할 합계가 전체 개수의 반틈보다 작으면 확인할 필요 없음
            #     continue
            if answer!=-1 and now_sum<answer: #현재 확인할 합계가 현재 찾은 답보다 작으면 확인할 필요 없음
                continue
            for r in range(m+1, len(cookie)):
                #m+1 ~ r
                check_sum = sum_list[r+1] - sum_list[m+1]
                if(check_sum == now_sum):
                    answer = max(answer, now_sum)
                    break
                elif(check_sum>now_sum):
                    break
    if answer == -1:
        answer = 0
    return answer

if __name__ == "__main__":
    cookie = [[1,1,2,3], [1,2,4,5]]

    for c in cookie:
        print(solution(c))
