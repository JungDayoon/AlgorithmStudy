import heapq

def dijkstra(graph, start, distances):
    queue = []
    heapq.heappush(queue, [0, start])

    while(queue):
        current_distance, current_node = heapq.heappop(queue)
        if(current_distance>distances[current_node]):
            continue
        for adjacent, weight in graph[current_node].items():
            distance = weight+current_distance
            if(distance<distances[adjacent]):
                distances[adjacent] = distance
                heapq.heappush(queue, [distance, adjacent])
    return distances
def calculate_distance(distances, end):
    min_num = 99999
    for i in distances.items():
        min_num = min(min_num,end-i[0]+i[1])
    return min_num
if __name__=="__main__":
    N, D = map(int, input().split())
    distances = {}
    mygraph = {}
    input_graph = []
    for i in range(N):
        start, end, weight = map(int, input().split())
        
        if(end<=D):
            input_graph.append([start, end, weight])
            distances[start] = start
            distances[end] = end
    distances[0] = 0
    distances[D] = D
    print(input_graph)
    print(distances)
    for i in distances:
        mygraph[i] = {}
    
    for i in input_graph:
        if(i[1] in mygraph[i[0]].keys()):
            mygraph[i[0]][i[1]] = min(mygraph[i[0]][i[1]], i[2])
        else:
            mygraph[i[0]][i[1]] = i[2]
    print(mygraph)

    result = dijkstra(mygraph, 0, distances)
    print(result)
    print(calculate_distance(result, D))
        



