def find(nown, startnum, nowr, nowc):
    if(nown == 1):
        return startnum
    half_n = int(nown/2)
    if(0<=nowr<half_n and 0<=nowc<half_n):
        return find(half_n, startnum, nowr, nowc)
        ##1번 공간 
    elif(0<=nowr<half_n and half_n<=nowc<nown):
        ##2번
        return find(half_n, startnum+half_n*half_n, nowr, nowc-half_n)
    elif(half_n<=nowr<nown and 0<=nowc<half_n):
        ##3번
        return find(half_n, startnum+half_n*half_n*2, nowr-half_n, nowc)
    else:
        ##4번 
        return find(half_n, startnum+half_n*half_n*3, nowr-half_n, nowc-half_n)


if __name__ == "__main__":
    N, r, c = map(int, input().split())

    n = 2**N
    print(find(n, 0, r, c))
