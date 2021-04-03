import sys
import heapq
if __name__ == "__main__":
    N = int(sys.stdin.readline())
    leftq = [] #max_heap
    rightq = [] #min_heap
    for i in range(N):
        num = int(sys.stdin.readline())
        if(len(leftq)==len(rightq)):
            heapq.heappush(leftq, (-num, num)) #max_heap으로 만들기 위해 음수로 변경한 값을 넣음
        else:
            heapq.heappush(rightq, (num, num))
        
        if rightq and rightq[0][1]<leftq[0][1]:
            leftnum1, leftnum2 = heapq.heappop(leftq)
            rightnum1, rightnum2 = heapq.heappop(rightq)
            heapq.heappush(leftq, (-rightnum1, rightnum1))
            heapq.heappush(rightq, (leftnum2, leftnum2))
        
        print(leftq[0][1])