import sys
import heapq
if __name__ == "__main__":
    T = int(sys.stdin.readline())
    for t in range(T):
        M = int(sys.stdin.readline())
        arr = []
        for cnt in range(M//10+1):
            new_list = list(map(int, sys.stdin.readline().split()))
            for j in new_list:
                arr.append(j)
        leftq, rightq = [], []
        answer = []
        for i in range(M):
            if(len(leftq)==len(rightq)):
                heapq.heappush(leftq, (-arr[i], arr[i]))
            else:
                heapq.heappush(rightq, (arr[i], arr[i]))
            
            if rightq and rightq[0][1]<leftq[0][1]:
                leftnum1, leftnum2 = heapq.heappop(leftq)
                rightnum1, rightnum2 = heapq.heappop(rightq)
                heapq.heappush(leftq, (-rightnum1, rightnum1))
                heapq.heappush(rightq, (leftnum2, leftnum2))
            if(i%2 == 0):
                answer.append(leftq[0][1])
        print(len(answer))
        for i in answer:
            print(i, end = " ")