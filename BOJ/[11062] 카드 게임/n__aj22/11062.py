def start_game(turn, start, end):
    if(start==end):
        if(turn%2 == 0):
            return card_list[start]
        else:
            return 0
    if(cache[start][end] != -1):
        return cache[start][end]
        
    if(turn%2 == 0):#turn 이 근우일 때
        answer = start_game(turn+1, start+1, end) + card_list[start]
        answer = max(start_game(turn+1, start, end-1)+card_list[end], answer)

    else:#turn 이 명우일 때
        answer = start_game(turn+1, start+1, end)
        answer = min(start_game(turn+1, start, end-1), answer)
    cache[start][end] = answer
    return answer

if __name__ == "__main__":
    T = int(input())
    for t in range(T):
        N = int(input())
        card_list = list(map(int, input().split()))
        cache = [[-1]*N for _ in range(N)]
        print(start_game(0, 0, N-1))

