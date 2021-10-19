import sys
sys.setrecursionlimit(10**4)
def init(bridge_num):
    bridge = {}
    for i in range(bridge_num):
        bridge[i+1] = {}
    return bridge


def move_possibility(w, index, end_point, visited, bridge):
    if(end_point == index):
        return True
    visited[index] = True
    for i in bridge[index]:
        if not visited[i] and bridge[index][i]>=w:
            flag = move_possibility(w, i, end_point, visited, bridge)
            if(flag):
                return True
    return False   


if __name__ == "__main__":
    N, M = map(int, input().split())
    bridge = init(N)
    max_w, min_w = 0, 0
    for i in range(M):
        u, v, w = map(int, input().split())
        max_w = max(w, max_w)
        if v in bridge[u].keys():
            bridge[u][v] = max(bridge[u][v], w)
            bridge[v][u] = max(bridge[v][u], w)
        else:
            bridge[u][v] = w
            bridge[v][u] = w
    start, end = map(int, input().split())

    while(min_w<=max_w):
        mid_w = (min_w+max_w)//2
        visited = [False]*(N+1)
        visited[start] = True
        check = move_possibility(mid_w, start, end, visited, bridge)

        if check:
            min_w = mid_w+1
        else:
            max_w = mid_w-1
        
    print(max_w)


