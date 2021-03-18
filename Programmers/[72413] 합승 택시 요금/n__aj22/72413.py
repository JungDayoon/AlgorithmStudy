import heapq
road = {}
def init_road(n):
    for i in range(1, n+1):
        road[i] = {}
    return
def make_road(fares):
    for fare in fares:
        c, d, f = fare
        road[c][d] = f
        road[d][c] = f
def dijkstra(start):
    distances = {node : float('inf') for node in road}
    distances[start] = 0
    queue = []
    heapq.heappush(queue, [distances[start],start])

    while(queue):
        current_distance, current_node = heapq.heappop(queue)
        if distances[current_node]<current_distance:
            continue # 현재 거리보다 이미 작으면 계산할 필요가 없음
        for adjacent, weight in road[current_node].items():
            distance = current_distance+weight
            if distance < distances[adjacent]:
                #계산한 거리가 기존 거리보다 작으면
                #업데이트
                distances[adjacent] = distance
                heapq.heappush(queue, [distance, adjacent])
    return distances


def solution(n, s, a, b, fares):
    answer = 0
    init_road(n)
    make_road(fares)
    shortest_paths = dijkstra(s)
    answer+=shortest_paths[a]
    answer+=shortest_paths[b]
    for k in range(1, n+1):
        if(s != k):
            distance = 0
            shortest_paths=dijkstra(s)
            distance+=shortest_paths[k]
            shortest_paths = dijkstra(k)
            distance+=shortest_paths[a]
            distance+=shortest_paths[b]
            answer = min(answer,distance)
    return answer
if __name__ == "__main__":
    n = [6, 7, 6]
    s = [4, 3, 4]
    a = [6, 4, 5]
    b = [2, 1, 6]
    fares = [[[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]],
    [[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]],
    [[2,6,6], [6,3,7], [4,6,7], [6,5,11], [2,5,12], [5,3,20], [2,4,8], [4,3,9]]]
    for i in range(3):
        road = {}
        print(solution(n[i], s[i], a[i], b[i], fares[i]))
