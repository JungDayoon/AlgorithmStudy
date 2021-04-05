import heapq
def calculate(arr):
    answer = 0
    while(len(arr)!=1):
        num1 = heapq.heappop(arr)
        num2 = heapq.heappop(arr)
        sum_num = num1+num2
        answer+=sum_num
        heapq.heappush(arr, sum_num)
    return answer
if __name__ == "__main__":
    N = int(input())
    queue = []

    for i in range(1, N+1):
        num = int(input())
        heapq.heappush(queue, num)
    
    print(calculate(queue))

