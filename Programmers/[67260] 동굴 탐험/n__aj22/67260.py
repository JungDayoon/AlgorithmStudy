#95.6 / 100.0
tree = {}
def init(n, path):
    global tree
    tree = {}
    for i in range(n):
        tree[i] = []

    for p in path:
        u, v = p
        tree[u].append(v)
        tree[v].append(u)
    return
# def make_tree(visited, node):
#     for child in tree[node]:
#         if(not visited[child]):
#             real_tree[node].append(child)
#             visited[child] = True
#             make_tree(visited, child)
    
#     return
    
def find_cycle(finished, check, node):
    if(finished[node]): return False
    if(check[node]): return True

    finished[node] = True
    check[node] = True
    for child in tree[node]:
        if(not check[child]):
            if not find_cycle(finished, check, child):
                return False
    finished[node] = False
    return True
def solution(n, path, order):
    answer = True
    init(n, path)
    
    for i in order:
        u, v = i
        if v == 0:
            return False
        tree[u].append(v)
    finished = [False]*n
    check = [False]*n
    answer = find_cycle(finished, check, 0)
    return answer
if __name__ == "__main__":
    n = 9
    paths = [[[0,1],[0,3],[0,7],[8,1],[3,6],[1,2],[4,7],[7,5]], [[8,1],[0,1],[1,2],[0,7],[4,7],[0,3],[7,5],[3,6]], [[0,1],[0,3],[0,7],[8,1],[3,6],[1,2],[4,7],[7,5]]]
    orders = [[[8,5],[6,7],[4,1]],[[4,1],[5,2]],[[4,1],[8,7],[6,5]]]

    for i in range(3):
        print(solution(n, paths[i], orders[i]))