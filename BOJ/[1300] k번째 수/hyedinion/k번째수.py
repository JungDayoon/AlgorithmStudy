n = int(input())
k = int(input())

# 1부터 n*n까지의 숫자중에 mid보다 작은 숫자의 갯수를 통해 k번째 숫자를 찾을것임 (이분탐색)
start, end = 1, k
answer =0

while start <= end:
    #mid = 숫자, k = 갯수, sum = 갯수
    mid = (start+end)//2

    _sum = 0
    for i in range(1,n+1):
        _sum+= min(mid//i,n)

    # sum이 k보다 크거나 같을 때 answer를 지정해 주는 이유
    # n=3일때 6보다 같거나 작은 수는 8개이다. (8번째 수가 6)
    # 하지만 7번째 수도 6이기 때문에 이때 answer를 지정해 주어야함
    # 5보다 같거나 작은 수는 6개 이기 때문에 answer가 확실히 아님을 알 수 있음
    # 즉, sum이 k보다 클 경우도 답이 될 수 있음. (같은숫자가 많을경우)
    if _sum>=k:
        answer = mid
        end = mid-1
    else:
        start = mid+1

print(answer)

#review
# 내가 이문제를 다시 마주치면 다시 풀 수 없을 것 같다.
# n보다 작은 숫자의 갯수를 이렇게 쉽게 찾을 수 있다는 것을 처음알았다.
# 그런데 뭔가 겹치는게 많아서 dp로 풀 수 있을 것 같은데 잘 안되서 힘들다.
# n보다 작은 수의 갯수를 알면 n+1보다 작은 수의 갯수를 더 쉽게 구할 순 없을까?


