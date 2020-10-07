import sys
sys.stdin = open("/Users/najihye/algo2/SWEA/[2105] 디저트 카페/n__aj22/input.txt", "r")
cafe_num = -1
dx = [1, -1, -1, 1]
dy = [1, 1, -1, -1]
def dfs(cafe, direction, starty,startx, nowy, nowx, visited, num, N, turn):
    global flag
    global cafe_num
    if(startx == nowx and starty == nowy and turn == 4):
        cafe_num = max(cafe_num, num)
        return
    if(cafe[nowy][nowx] in visited): # 방문한 적 있는 곳이면
        return
    else:  
        visited.append(cafe[nowy][nowx])#방문한 곳으로 추가해줌
        nextx = nowx + dx[direction]
        nexty = nowy + dy[direction]
        if not (nextx == N or nexty == N or nextx == -1 or nexty == -1):
            dfs(cafe, direction, starty,startx,  nexty, nextx, visited, num+1, N, turn)      
                
        nextx = nowx + dx[(direction+1)%4]
        nexty = nowy + dy[(direction+1)%4]
        if not (nextx == N or nexty == N or nextx == -1 or nexty == -1):
            dfs(cafe, (direction+1)%4, starty,startx,  nexty, nextx, visited, num+1, N, turn+1)

        visited.remove(cafe[nowy][nowx]) 
        return
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N = int(input())
    cafe = []
    for i in range(N):
        cafe.append(list(map(int, input().split())))
    cafe_num = -1
    for i in range(N):
        for j in range(N):
            dfs(cafe, 0,i, j,i,j,[], 0,N, 0)
    print("#"+str(test_case)+" "+str(cafe_num))
    

    
