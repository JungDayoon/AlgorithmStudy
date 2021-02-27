import heapq
def dijkstra(graph, start, maxtime, option):
    distances = {node: [float('inf'), float('inf')] for node in graph}
    distances[start][0] = 0 #시간
    distances[start][1] = 0 #돈
    queue = []
    heapq.heappush(queue, [distances[start], start])
    if(option == 1):
        while(queue):
            time_money, current_node = heapq.heappop(queue)
            if(distances[current_node][1]<time_money[1]):
                continue
            for adjacent, weight in graph[current_node].items():
                #distance = current_distance+weight
                distance_money = time_money[1] + weight[1]
                distance_time = time_money[0]+weight[0]
                if(distance_time<distances[adjacent][0]):
                    distances[adjacent][0] = distance_time
                    distances[adjacent][1] = distance_money

                    heapq.heappush(queue, [distances[adjacent], adjacent])
    else:
        while(queue):
            time_money, current_node = heapq.heappop(queue)
            if(distances[current_node][0]<time_money[0]):
                continue
            for adjacent, weight in graph[current_node].items():
                #distance = current_distance+weight
                distance_money = time_money[1] + weight[1]
                distance_time = time_money[0]+weight[0]
                if(distance_money<distances[adjacent][1]):
                    distances[adjacent][0] = distance_time
                    distances[adjacent][1] = distance_money

                    heapq.heappush(queue, [distances[adjacent], adjacent])

    return distances


if __name__=="__main__":
    N = int(input())#건물 
    T, M = map(int, input().split()) #시간, 돈
    L = int(input()) # 노드수 
    mygraph = {}
    for i in range(N):
        mygraph[i+1]= {}
    for i in range(L):
        start, end, t, m = map(int, input().split())
        mygraph[start][end] = [t, m]
        mygraph[end][start] = [t, m]
    ##1번 건물에서 N번 건물까지!
    result = dijkstra(mygraph, 1, T,1)
    result2 = dijkstra(mygraph, 1, T,2)
    minmoney = []
    if(result[N][1]<=M and result[N][0]<=T):
        minmoney.append(result[N][1])
    if(result2[N][1]<=M and result2[N][0]<=T):
        minmoney.append(result2[N][1])

    if(len(minmoney)==0):
        print(-1)
    else:
        print(min(minmoney))
    
