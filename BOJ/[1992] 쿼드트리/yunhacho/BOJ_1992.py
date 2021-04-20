def QuadTree(strtx, strty, endx, endy, n, video):
    if n==1:
        return video[strty][strtx]
    length=n//2
    partition1=QuadTree(strtx, strty, strtx+length, strty+length, length, video)
    partition2=QuadTree(strtx+length, strty, strtx+n, strty+length, length, video)
    partition3=QuadTree(strtx,strty+length, strtx+length, strty+n, length, video)
    partition4=QuadTree(strtx+length, strty+length, strtx+n, strty+n, length, video)

    if partition1==partition2==partition3==partition4 and len(partition1)==1:
        return partition1
    return ("("+ partition1+partition2+partition3+partition4+")")


if __name__=="__main__":
    N=int(input())
    video=[input() for _ in range(N)]
    print(QuadTree(0,0,N,N,N,video))