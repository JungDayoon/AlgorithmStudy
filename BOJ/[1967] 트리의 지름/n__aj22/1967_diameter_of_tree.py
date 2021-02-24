tree = {}
def init():
    for i in range(N):
        tree[i+1] = []
def find_max(start):
    visited = [-1]*(N+1)
    visited[start] = 0
    queue = []
    queue.append([start, 0])
    max_vertex, max_weight = start, 0
    while(queue):
        vertex, now_weight = queue.pop(0)

        linked_list = tree[vertex]
        for node in linked_list:
            child, weight = node
            if(visited[child] == -1):
                visited[child] = now_weight+weight
                queue.append([child, now_weight+weight])
                if(max_weight<now_weight+weight):
                    max_vertex = child
                    max_weight = now_weight+weight
    return max_vertex, max_weight

if __name__ == "__main__":
    N = int(input())
    init()
    for i in range(N-1):
        parent, child, weight = map(int, input().split())
        tree[parent].append([child, weight])
        tree[child].append([parent, weight])
    first_vertex, weight = find_max(1)
    second_vertex, weight = find_max(first_vertex)
    print(weight)
    
    
