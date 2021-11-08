import sys
input = sys.stdin.readline

N = int(input())
play_result = [ list(map(int,input().split())) for _ in range(N)]
player_list = [0,0,0,0,0,0,0,0,0]
check = [True for _ in range(9)];check[0]=False
answer = 0

def play_game(player_list):
    global answer
    temp_answer = 0
    current_num = 0
    for p in play_result:
        b1,b2,b3=0,0,0
        out = 0
        while True:
            player = p[player_list[current_num]]
            if player==0:
                out+=1
            elif player==1:
                temp_answer+=b3
                b1,b2,b3 = 1,b1,b2
            elif player==2:
                temp_answer+=b2+b3
                b1,b2,b3 = 0,1,b1
            elif player==3:
                temp_answer+=b1+b2+b3
                b1,b2,b3=0,0,1
            elif player==4:
                temp_answer+=b1+b2+b3+1
                b1,b2,b3 = 0,0,0
            current_num = (current_num+1) %9
            if out==3:
                break
    answer = max(answer,temp_answer)
    return

def dfs(depth,check,player_list):
    if depth==9:
        play_game(player_list)
        return
    for i in range(1,9):
        if check[i]:
            player_list[depth] = i
            check[i]=False
            if depth==2:
                dfs(depth+2,check,player_list)
            else:
                dfs(depth+1,check,player_list)
            player_list[depth] =0
            check[i]=True
    return

dfs(0,check,player_list)
print(answer)