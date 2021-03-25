#시간 초과 60/100
from itertools import permutations
import copy
def go_fix(choose_friend, weak, n):
    for start_index in range(len(weak)):
        visited = [False]*len(weak)

        start = start_index
        s_next = start + 1
        now_point = weak[start]
        if(s_next == len(weak)):
            s_next = 0
        
        for d in choose_friend:
            visited[start] = True
            for i in range(d):
                now_point+=1
                if(now_point == n):
                    now_point = 0
                if(now_point == weak[s_next]):
                    visited[s_next]=True
                    s_next+=1
                    if(s_next==len(weak)):
                        s_next = 0
            now_point = weak[s_next]   
            start = s_next
            s_next +=1
            if(s_next==len(weak)):
                s_next = 0     

        if False not in visited:
            return True

    return False

def init_visited(weak):
    visited = {}
    for i in weak:
        visited[i] = False
    return visited
def solution(n, weak, dist):
    answer = 0
    friends = len(dist)
    dist.sort(reverse=True)
    for i in range(1, friends+1):
        choose_friends = dist[0:i]
        perm = list(permutations(choose_friends, i))
        # print(weak_friends, start_list)
        # i명 선택 시 
        for start in perm:
            flag = go_fix(start, weak, n)
            if(flag):
                return i
    return -1
if __name__ == "__main__":
    n = 12
    weak = [[1, 5, 6, 10], [1, 3, 4, 9, 10]]
    dist = [[1, 2, 3, 4], [3, 5, 7]]

    for i in range(2):
        print(solution(n, weak[i], dist[i]))