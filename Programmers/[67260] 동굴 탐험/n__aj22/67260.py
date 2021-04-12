#95.6 / 100.0
import sys
sys.setrecursionlimit(10**6)
tree = {}
real_tree = {}
def init(n, path):
    global tree, real_tree
    tree = {}
    real_tree = {}
    for i in range(n):
        tree[i] = []
        real_tree[i] = []

    for p in path:
        u, v = p
        tree[u].append(v)
        tree[v].append(u)
    return
def make_tree(visited, node):
    for child in tree[node]:
        if(not visited[child]):
            real_tree[node].append(child)
            visited[child] = True
            make_tree(visited, child)
    
    return
    
def find_cycle(visited, check, node):
    if(visited[node]): return False
    if(check[node]): return True

    visited[node] = True
    check[node] = True
    for child in real_tree[node]:
        if not find_cycle(visited, check, child):
            return False
    visited[node] = False
    return True
def solution(n, path, order):
    global real_tree
    answer = True
    init(n, path)

    visited = [False]*n
    visited[0] = True
    make_tree(visited, 0)
    
    for i in order:
        u, v = i
        if v == 0:
            return False
        real_tree[u].append(v)
    visited = [False]*n
    check = [False]*n
    answer = find_cycle(visited, check, 0)
    return answer
if __name__ == "__main__":
    n = 9
    paths = [[[0,1],[0,3],[0,7],[8,1],[3,6],[1,2],[4,7],[7,5]], [[8,1],[0,1],[1,2],[0,7],[4,7],[0,3],[7,5],[3,6]], [[0,1],[0,3],[0,7],[8,1],[3,6],[1,2],[4,7],[7,5]]]
    orders = [[[8,5],[6,7],[4,1]],[[4,1],[5,2]],[[4,1],[8,7],[6,5]]]

    for i in range(3):
        print(solution(n, paths[i], orders[i]))