dy = [0, 0, -1, 1]
dx = [1, -1, 0, 0]
N, K = 0, 0
def isin(y, x):
    global N
    if(0<=y<N and 0<=x<N):
        return True
    return False
def move(y, x, d):
    return y+dy[d], x+dx[d]

def make_key(y, x):
    return str(y)+" "+str(x)

def solve_key(key):
    y, x = key.split()
    return y, x
def reverse(d):
    if d == 0:
        return 1
    elif d == 1:
        return 0
    elif d == 2:
        return 3
    else:
        return 2
def move_from_a_to_b(a, b, horse, dic, color):
    A_key = make_key(a[0], a[1])
    B_key = make_key(b[0], b[1])

    for h in dic[A_key]:
        horse[h][0] = b[0]
        horse[h][1] = b[1]
    if color == 1:
        dic[A_key].reverse()

    if(B_key in dic):
        dic[B_key].extend(dic[A_key])
    else:
        dic[B_key] = dic[A_key]
    del dic[A_key]


    if(len(dic[B_key])>=4):
        return False
    return True


def start_game(chess_board, horse, dic):
    global K
    t = 1
    while(t<=10):
        for i in range(K):
            y, x, d = horse[i]
            key = make_key(y, x)
            if dic[key][0] != i: #첫번째가 아니면 움직일 수 없다.
                continue
            nexty, nextx = move(y, x, d)
            if(not isin(nexty, nextx) or chess_board[nexty][nextx]==2): #wall or blue
                d = reverse(horse[i][2])
                nexty, nextx = move(y, x, d)
                if(not isin(nexty, nextx) or chess_board[nexty][nextx] == 2): # blue too 
                    horse[i][2] = d
                else : #white or red
                    horse[i][2] = d
                    if not move_from_a_to_b([y, x], [nexty, nextx], horse, dic, chess_board[nexty][nextx]):
                        return t
            else: #white or red
                if not move_from_a_to_b([y, x], [nexty, nextx], horse, dic, chess_board[nexty][nextx]):
                    return t
        t+=1
    return -1

if __name__ == "__main__":
    N, K = map(int, input().split())
    chess_board = [list(map(int, input().split())) for _ in range(N)]
    horse = []
    dic = {}
    for i in range(K):
        y, x, d = map(int, input().split())
        horse.append([y-1, x-1, d-1])
        key = make_key(y-1, x-1)
        if(key not in dic):
            dic[key] = [i]
        else:
            dic[key].append(i)

    
    print(start_game(chess_board, horse, dic))