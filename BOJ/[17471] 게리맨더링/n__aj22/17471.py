import itertools
def check_bundle(index):
    visited[index] = True
    queue = []
    queue.append(index)
    population = people[index-1]
    while(queue):
        node = queue.pop(0)
        for i in arr[node-1]:
            if not visited[i]:
                population+=people[i-1]
                visited[i] = True
                queue.append(i)
    
    return population
def possible(temp):
    if len(temp)==1:
        return True
    queue = []
    visited2 = {}
    for i in temp:
        visited2[i] = False
    visited2[temp[0]] = True
    queue.append(temp[0])

    while(queue):
        node = queue.pop(0)
        for i in arr[node-1]:
            if i in temp and not visited2[i]:
                visited2[i] = True
                queue.append(i)
    
    if False in visited2.values():
        return False
    return True

def divid():
    temp = [n for n in range(1, N+1)]
    answer = float('inf')
    for i in range(1, N):
        col_list = list(itertools.combinations(temp, i))
        for col in col_list:
            if 1 in col:
                arr1 = list(col)
                arr2 = [n for n in range(1, N+1) if n not in arr1]
                if(possible(arr1) and possible(arr2)):
                    sum1 = 0
                    for i in arr1:
                        sum1+=people[i-1]
                    sum2 = 0
                    for i in arr2:
                        sum2+=people[i-1]
                    answer = min(answer, abs(sum1-sum2))
    return answer
if __name__ == '__main__':
    N = int(input())
    people = list(map(int, input().split()))
    arr = []
    for i in range(N):
        arr.append(list(map(int, input().split()))[1:])
    visited = [False]*(N+1)
    chk = 0
    population_list = []
    for i in range(1, N+1):
        if not visited[i]:
            chk+=1
            population_list.append(check_bundle(i))
    if(chk>2):
        print(-1)
    elif(chk == 2):
        print(abs(population_list[0]-population_list[1]))
    else:
        print(divid())