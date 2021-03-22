import sys
sys.setrecursionlimit(10**5)
tree = {}
dp = []
dp_info = []
def init():
    dp.append([0, 0])
    dp_info.append([[],[]])
    for i in range(1, n+1):
        tree[i] = []
        dp.append([0, 0])
        dp_info.append([[],[]])
def make_tree(node):
    visited[node] = True
    dp[node][0] = weight[node-1]
    dp_info[node][0].append(node)
    for child in tree[node]:
        if not visited[child]:
            make_tree(child)
            #dp 0번째 -> 포함하는 경우
            dp[node][0]+=dp[child][1]
            for a in dp_info[child][1]:
                dp_info[node][0].append(a)
            
            #dp 1번째 -> 포함안하는 경우 
            if(dp[child][0]>dp[child][1]):
                dp[node][1]+=dp[child][0]
                for a in dp_info[child][0]:
                    dp_info[node][1].append(a)
            else:
                dp[node][1]+=dp[child][1]
                for a in dp_info[child][1]:
                    dp_info[node][1].append(a)

    return
if __name__ == "__main__":
    n = int(input())
    weight = list(map(int,sys.stdin.readline().split()))
    visited = [False]*(n+1)
    init()
    for i in range(n-1):
        u, v = map(int, sys.stdin.readline().split())
        tree[u].append(v)
        tree[v].append(u)
    make_tree(1)
    if(dp[1][0]>dp[1][1]):
        print(dp[1][0])
        dp_info[1][0].sort()
        for num in dp_info[1][0]:
            print(num, end=" ")
    else:
        print(dp[1][1])
        dp_info[1][1].sort()
        for num in dp_info[1][1]:
            print(num, end=" ")

