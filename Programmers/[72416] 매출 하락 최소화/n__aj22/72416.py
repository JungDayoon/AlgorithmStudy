from itertools import combinations
graph = {}
visited = []
dp = []
def init(employee_num):
    global graph, visited, dp
    graph = {}
    visited = [False]*(employee_num+1)
    dp = [[0]*2 for _ in range(employee_num+1)]
    for i in range(employee_num):
        graph[i+1] = []
    return
def find(node, sales):
    visited[node] = True
    dp[node][0] = sales[node-1]
    me_go = 0
    min_child = 0
    child_key = []
    for child in graph[node]:
        if(not visited[child]):
            find(child, sales)
            me_go+=min(dp[child])
            child_key.append(child)
            #min_child = min(min_child, dp[child][0])
    if(len(child_key)!=0):
        #min_child 합 찾기
        for key in child_key:
            min_child += dp[key][0]
        for i in range(1, len(child_key)):
            combs = list(combinations(child_key, i))
            for comb in combs:
                check_child = 0
                for key in child_key:
                    if key in comb:
                        check_child+=dp[key][1]
                    else:
                        check_child+=dp[key][0]
                min_child = min(min_child, check_child)
        
        dp[node][0]+=me_go
        dp[node][1] = min_child
    return
def solution(sales, links):
    answer = 0
    employee = len(sales)
    init(employee)
    for node in links:
        u, v = node
        graph[u].append(v)
        graph[v].append(u)
    find(1, sales)
    answer = min(dp[1])
    return answer

if __name__ == "__main__":
    sales = [[14, 17, 15, 18, 19, 14, 13, 16, 28, 17],
    [5, 6, 5, 3, 4], [5, 6, 5, 1, 4], [10, 10, 1, 1]]
    links = [[[10, 8], [1, 9], [9, 7], [5, 4], [1, 5], [5, 10], [10, 6], [1, 3], [10, 2]],
    	[[2,3], [1,4], [2,5], [1,2]],
        	[[2,3], [1,4], [2,5], [1,2]], 	[[3,2], [4,3], [1,4]]]
    
    for i in range(4):
        print(solution(sales[i], links[i]))