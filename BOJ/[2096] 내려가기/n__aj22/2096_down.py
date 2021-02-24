if __name__ == "__main__":
    N = int(input())
    maxscore = [0]*3
    minscore = [0]*3
    for i in range(N):
        line = list(map(int, input().split()))
        if(i == 0):
            maxscore = line
            minscore = line
        else:
            maxscore = [max(maxscore[0], maxscore[1])+line[0], max(maxscore[0], maxscore[1], maxscore[2])+line[1], max(maxscore[1], maxscore[2])+line[2]]
            minscore = [min(minscore[0], minscore[1])+line[0], min(minscore[0], minscore[1], minscore[2])+line[1], min(minscore[1], minscore[2])+line[2]]
    print(max(maxscore), min(minscore))