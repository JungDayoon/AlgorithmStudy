board = []
arr = list(map(int,input().split()))
max_score = 0

def backtracking(now, score, position):
    global max_score
    if(now == 10):
        max_score = max(max_score, score)
        return

    for i in range(4):
        if(position[i][0]!=-1):
            flag = True
            temp = position[i]
            board_type, point = horse_next_move(position[i][0], position[i][1], arr[now])
            if(board_type != -1):
                for j in range(4):
                    if(i!=j and position[j][0]!=-1):
                        if(position[j][0]== board_type and position[j][1] == point):
                            flag = False
                            break
            if(flag == False):
                continue
            score+=point

            position[i] = [board_type, point]
            backtracking(now+1, score, position)
            score-=position[i][1]
            position[i] = temp
def horse_next_move(board_type, now_point, move):
    now_board = board[board_type]
    index_of_now_point = now_board.index(now_point)
    index_of_next_point = index_of_now_point+move
    
    if(index_of_next_point >= len(now_board)):
        return -1, 0 #끝에 도달 
    if(now_board[index_of_next_point]==-1):
        return -1, 0
    point = now_board[index_of_next_point]
    if(board_type == 0 and point%10 == 0):
        board_type = point//10
    elif(point == 40):
        board_type = 4
    elif((board_type == 1 or board_type == 2 or board_type == 3) and (point == 25 or point>=30)):
        board_type = 5

    
    return board_type, point

def set_board():
    # default map
    new_arr = []
    for i in range(21):
        new_arr.append(i*2)
    new_arr.append(-1)
    board.append(new_arr)

    # 10 map
    new_arr = [10, 13, 16, 19, 25, 30, 35, 40, -1]
    board.append(new_arr)

    # 20 map
    new_arr = [20, 22, 24, 25, 30, 35, 40, -1]
    board.append(new_arr)

    # 30 map
    new_arr = [30, 28, 27, 26, 25, 30, 35, 40, -1]
    board.append(new_arr)

    # 40 map
    new_arr = [40, -1]
    board.append(new_arr)

    # 25 map
    new_arr = [25, 30, 35, 40, -1]
    board.append(new_arr)



if __name__ =="__main__":
    set_board()
    backtracking(0, 0, [[0,0],[0,0],[0,0],[0,0]])
    print(max_score)
