num = {}
classify = {}

def solution(genres, plays):
    answer = []
    song_num = len(genres)

    for i in range(song_num):
        if genres[i] in num.keys():
            num[genres[i]]+=plays[i]
            classify[genres[i]].append([i, plays[i]])
        else:
            num[genres[i]]=plays[i]
            classify[genres[i]] = [[i, plays[i]]]

    num_val_reverse = sorted(num.items(), 
                              reverse=True, 
                              key=lambda item: item[1])
    for key, value in num_val_reverse:
        check_song = classify[key]
        check_song.sort(key=lambda x:(-x[1], x[0]))
        count = 0
        for i in range(len(check_song)):
            answer.append(check_song[i][0])
            count+=1
            if(count == 2):
                break

    return answer

if __name__ == "__main__":
    genre = ["classic", "pop", "classic", "classic", "pop"]
    play = [500, 600, 150, 800, 2500]

    print(solution(genre, play))