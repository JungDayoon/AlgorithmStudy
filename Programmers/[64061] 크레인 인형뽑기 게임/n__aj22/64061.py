def choose_friends(board, x, n):
    for i in range(n):
        if(board[i][x]!=0):
            friend = board[i][x]
            board[i][x] = 0
            return friend
    return -1


def solution(board, moves):
    answer = 0
    n = len(board)
    basket = []
    for move in moves:
        chk = choose_friends(board, move-1, n)
        if(chk!=-1):
            if(len(basket)==0):
                basket.append(chk)
            else:
                if(basket[-1]==chk):
                    answer+=2
                    basket.pop(-1)
                else:
                    basket.append(chk)

    return answer
if __name__ == "__main__":
    board = [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]]
    moves = [1,5,3,5,1,2,1,4]

    print(solution(board, moves))