import sys
from itertools import combinations
sys.setrecursionlimit(10**4)
town = {}
dp = []
def init():
    global town, dp
    dp.append([0, 0])
    for i in range(1, N+1):
        town[i] = []
        dp.append([0, 0])

def find_max_town(node):
    visited[node] = True
    dp[node][0] = people[node-1]
    key = []
    for child in town[node]:
        if(not visited[child]):
            find_max_town(child)
            key.append(child)
            dp[node][0]+=dp[child][1]
    if(len(key)!=0):
        for i in range(len(key)+1):
            combs = combinations(key, i) #우수마을
            for comb in list(combs):
                people_sum = 0
                for child in key:
                    if child in comb:
                        people_sum += dp[child][0]
                    else:
                        people_sum += dp[child][1]
                dp[node][1] = max(dp[node][1], people_sum)

    return 

if __name__ == "__main__":
    N = int(input())
    init()
    people = list(map(int, input().split()))
    visited = [False]*(N+1)
    for i in range(N-1):
        u, v = map(int, input().split())
        town[u].append(v)
        town[v].append(u)
    
    find_max_town(1)
    print(max(dp[1]))