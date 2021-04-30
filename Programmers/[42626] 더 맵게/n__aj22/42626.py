import heapq
def solution(scoville, K):
    answer = 0
    heapq.heapify(scoville)
    while(True):
        if(scoville[0]>=K):
            break
        if len(scoville)<2:
            return -1
        n1 = heapq.heappop(scoville)
        n2 = heapq.heappop(scoville)
        heapq.heappush(scoville, n1+n2*2)
        answer+=1
    return answer

if __name__ == "__main__":
    scov = [1, 2, 3, 9, 10, 12]
    K = 7
    print(solution(scov, K))