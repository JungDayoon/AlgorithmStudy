import sys
input = sys.stdin.readline
T = int(input())
D = [[1,1],[1,-1],[-1,-1],[-1,1]]
answer=-1

def dfs(N,I,J,i,j,space,direct_num,Move,disert_list):#시작 index(I,J)
    global answer
    
    #방향 전환(1,2번만)
    if direct_num<2:
        
        #움직이기
        #계속 전진
        i_forward,j_forward = i+D[direct_num][0],j+D[direct_num][1]
        if 0<=i_forward<N and 0<=j_forward<N and space[i_forward][j_forward] not in disert_list:
            #디저트 먹기
            d_list = disert_list[:]
            d_list.append(space[i_forward][j_forward])
            new_move = Move[:]
            new_move[direct_num] +=1
            temp = dfs(N,I,J,i_forward,j_forward,space,direct_num,new_move,d_list)
            answer = max(answer,temp)
        if Move[0]>0:
            i_next, j_next = i+D[direct_num+1][0],j+D[direct_num+1][1]
            if 0<=i_next<N and 0<=j_next<N and space[i_next][j_next] not in disert_list:
                direct_num+=1
                temp = -1
                if direct_num==2:
                    temp = dfs(N,I,J,i,j,space,direct_num,Move,disert_list)
                else:
                    #디저트 먹기
                    d_list = disert_list[:]
                    d_list.append(space[i_next][j_next])
                    new_move = Move[:]
                    new_move[direct_num]+=1
                    temp = dfs(N,I,J,i_next,j_next,space,direct_num,new_move,d_list)
                answer = max(answer,temp)

    else:#종료
        if Move[0]<1 or Move[1]<1:
            return -1
        for k in range(2):
            for m in range(1,Move[k]+1):
                #1,2번 증가만큼-> 3,4번 증가
                i_forward,j_forward = i+D[k+2][0],j+D[k+2][1]
                if i_forward==I and j_forward==J:
                    return len(disert_list)
                #bound 안넘으면
                if 0<=i_forward<N and 0<=j_forward<N and space[i_forward][j_forward] not in disert_list:
                    i,j = i_forward,j_forward
                    disert_list.append(space[i_forward][j_forward])
                else:
                    return -1
    return answer


for t in range(T):
    N = int(input())
    answer = -1
    space = [ list(map(int,input().split())) for _ in range(N)]
    for i in range(N-1):
        for j in range(N-1):
            Move = [0,0,0,0]
            disert_list = [space[i][j]]
            answer = max(answer,dfs(N,i,j,i,j,space,0,Move,disert_list))

    print("#{} {}".format(t+1,answer))
