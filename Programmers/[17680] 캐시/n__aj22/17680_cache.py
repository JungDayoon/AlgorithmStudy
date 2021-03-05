import collections
hit = 1
miss = 5
#LRU 알고리즘 구현 
def solution(cacheSize, cities):
    answer = 0
    q = collections.deque()
    if cacheSize != 0:
        for city in cities:
            if(city.lower() not in q):#q 안에 존재하지 않는 경우 
                answer+=miss
                if(len(q)>cacheSize-1):
                    q.popleft()
                q.append(city.lower())
            else: #q 안에 존재하는 경우 
                answer+=hit
                q.remove(city.lower())
                q.append(city.lower())
        return answer
    else:
        return len(cities)*miss
if __name__ == "__main__":
    cachesize = [3, 3, 2, 5, 2, 0]
    city = [["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"],
    ["Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"],
    ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"],
    ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"],
    ["Jeju", "Pangyo", "NewYork", "newyork"],
    ["Jeju", "Pangyo", "Seoul", "NewYork", "LA"]]

    for i in range(6):
        print(solution(cachesize[i], city[i]))