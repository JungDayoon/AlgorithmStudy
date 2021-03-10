dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def isin(y, x):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def open_door(visited, y, x):
    queue =[]
    queue.append([y, x])
    visited[y][x] = True
    same_country = [[y, x]]
    sum_people = arr[y][x]
    while(queue):
        y, x = queue.pop(0)
        for i in range(4):
            nexty = y+dy[i]
            nextx = x+dx[i]
            if(isin(nexty, nextx)):
                if(L<=abs(arr[y][x]-arr[nexty][nextx])<=R and not visited[nexty][nextx]):
                    visited[nexty][nextx] = True
                    queue.append([nexty, nextx])
                    same_country.append([nexty, nextx])
                    sum_people+=arr[nexty][nextx]

    if(len(same_country)>1):
        return True, same_country, sum_people
    else:
        return False, same_country, sum_people

def move_people():
    answer = 0
    while(True):
        visited = [[False]*N for _ in range(N)]
        flag = False
        for i in range(N):
            for j in range(N):
                if(not visited[i][j]):
                    ispossible, country, people_num = open_door(visited, i, j)
                    if(ispossible):
                        flag = True
                        #이동 시켜주기
                        divided_people = people_num//len(country)
                        for a in country:
                            arr[a[0]][a[1]] = divided_people
        if(flag == False):
            return answer
        answer+=1

                #opendoor
if __name__ == "__main__":
    N, L, R = map(int, input().split())
    arr=[]
    for i in range(N):
        arr.append(list(map(int, input().split())))
    print(move_people())