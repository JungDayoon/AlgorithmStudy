import sys
sys.setrecursionlimit(10**5)
tree = {}
def init():
    for i in range(1, N+1):
        tree[i] = []
def make_tree(node):
    if(visited[node]):
        return dp[node]
    visited[node] = True
    dp[node] = 1
    for child in tree[node]:
        if not visited[child]:
            make_tree(child)
            dp[node]+= dp[child]
    return

if __name__ == "__main__":
    N, R, Q = map(int,sys.stdin.readline().split())
    visited = [False]*(N+1)
    init()
    dp = [0]*(N+1)
    for i in range(N-1):
        u, v = map(int,sys.stdin.readline().split())
        tree[u].append(v)
        tree[v].append(u)
    make_tree(R)
    
    for i in range(Q):
        q = int(sys.stdin.readline())
        print(dp[q])

