def possibility(num, stones, k):
    count = 0
    for stone in stones:
        if(stone-num<0):
            count+=1
        else:
            count = 0
        if(count==k):
            return False
    return True
def solution(stones, k):
    answer = 0
    end = max(stones)+1
    start = 1 #한 명은 건널 수 있음 
    while(True):
        if(start == end - 1):
            answer = start
            break
        mid = (start+end)//2
        if(possibility(mid, stones, k)):
            start = mid
        else:
            end = mid

    return answer
if __name__ == "__main__":
    stones = [2, 4, 5, 3, 2, 1, 4, 2, 5, 1]
    k = 3
    print(solution(stones, k))