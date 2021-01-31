if __name__ == "__main__":
    N, M = map(int, input().split())

    s = set()
    for i in range(N):
        s.add(input())
    cnt = 0
    for i in range(M):
        word = input()
        if(word in s):
            cnt+=1
    print(cnt)

