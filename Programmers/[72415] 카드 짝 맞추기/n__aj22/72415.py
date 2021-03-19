from itertools import permutations
dc = [-1, 0, 1, 0]
dr = [0, -1, 0, 1]#왼위오아
card_map = {}
card_order = []
min_move = float('inf')
def init_card(board):
    global card_map, card_order
    card_map = {}
    card_order = []
    for i in range(4):
        for j in range(4):
            if(board[i][j]!=0):
                card_num = board[i][j]
                if(card_num in card_map.keys()):
                    card_map[card_num].append([i, j])
                else:
                    card_map[card_num] = [[i, j]]
    card_order = [key for key in card_map.keys()]
    card_order = list(permutations(card_order))
    return
def isin(r, c):
    if(0<=r<4 and 0<=c<4):
        return True
    return False
def ctrl_move(r, c, direction, board):
    nowr, nowc = r, c
    while(True):
        nextr, nextc = nowr+dr[direction], nowc+dc[direction]
        if(not isin(nextr, nextc)):
            return nowr, nowc
        if(board[nextr][nextc]!=0):
            return nextr, nextc   
        nowr, nowc = nextr, nextc

def find_card(r, c, target_card, board):#다음카드 까지 가는데 걸리는 거리 bfs로, 다음 위치도 return
    queue = []
    sr, sc = r, c
    if([r, c] == target_card):
        return sr, sc, 1
    queue.append([r, c])
    visited = [[-1]*4 for _ in range(4)]
    visited[r][c] = 0
    while(queue):
        r, c = queue.pop(0)
        for i in range(4):
            nextr, nextc = r+dr[i], c+dc[i]
            if(isin(nextr, nextc)):
                if(visited[nextr][nextc]==-1):
                    visited[nextr][nextc] = visited[r][c]+1
                    if([nextr, nextc] == target_card):
                        return nextr, nextc, visited[nextr][nextc]+1
                    queue.append([nextr,nextc])
            nextr, nextc = ctrl_move(r, c, i, board)
            if(visited[nextr][nextc]==-1):
                visited[nextr][nextc] = visited[r][c]+1
                if([nextr, nextc] == target_card):
                    return nextr, nextc, visited[nextr][nextc]+1
                queue.append([nextr, nextc])
    return sr, sc, int(1e4)
def backtracking(board, r, c, moved_distance, index, order_list):
    if(index == len(card_map.keys())):
        global min_move
        min_move = min(moved_distance, min_move)
        return
    
    #card_map[index]의 첫 번째 카드 -> 두 번째 카드 선택 후 다음카드
    card_num = order_list[index]
    nr, nc, dis = find_card(r, c, card_map[card_num][0], board)
    nr2, nc2, dis2 = find_card(nr, nc, card_map[card_num][1], board)
    board[nr][nc], board[nr2][nc2] = 0, 0
    backtracking(board, nr2, nc2, moved_distance+dis+dis2, index+1, order_list)
    board[nr][nc], board[nr2][nc2] = card_num, card_num

    #card_map[index]의 두 번째 카드 -> 첫 번째 카드 선택 후 다음카드

    nr, nc, dis = find_card(r, c, card_map[card_num][1], board)
    nr2, nc2, dis2 = find_card(nr, nc, card_map[card_num][0], board)
    board[nr][nc], board[nr2][nc2] = 0, 0
    backtracking(board, nr2, nc2, moved_distance+dis+dis2, index+1, order_list)
    board[nr][nc], board[nr2][nc2] = card_num, card_num

    return


def solution(board, r, c):
    global card_map, card_order, min_move
    answer = 0
    min_move = float('inf')
    init_card(board)
    
    for order in card_order:
        backtracking(board, r, c, 0, 0, order)
    answer = min_move
    return answer

if __name__ == "__main__":
    board = [[[1,0,0,3],[2,0,0,0],[0,0,0,2],[3,0,1,0]], [[3,0,0,2],[0,0,1,0],[0,1,0,0],[2,0,0,3]]]
    r = [1, 0]
    c = [0, 1]
    for i in range(2):
        print(solution(board[i], r[i], c[i]))