def solution(sticker):
    answer = 0
    sticker_num = len(sticker)
    
    if(sticker_num == 1):
        return sticker[0]
    else:
        visited = [0]*sticker_num
        visited[0] = sticker[0]
        visited[1] = visited[0]
        for i in range(2, sticker_num-1):
            visited[i] = max(visited[i-1], visited[i-2]+sticker[i])
        
        max_sum = max(visited)

        visited2 = [0]*sticker_num
        visited2[0] = 0
        visited2[1] = sticker[1]
        for i in range(2, sticker_num):
            visited2[i] = max(visited2[i-1], visited2[i-2]+sticker[i])
        max_sum2 = max(visited2)
    return max(max_sum, max_sum2)

if __name__ == "__main__":
    sticker = [[14, 6, 5, 11, 3, 9, 2, 10], [1, 3, 2, 5, 4]]

    for i in range(2):
        print(solution(sticker[i]))