def count(dist):
    c = 0
    for i in range(N+1):
        c += (rest_point[i+1]-rest_point[i]-1)//dist
    return c
if __name__ == "__main__":
    N, M, L = map(int, input().split())

    rest_point = list(map(int, input().split()))
    rest_point.append(0)
    rest_point.sort()
    max_length = 0
    rest_point.append(L)

    start, end = 0, L

    while(start<=end):
        mid = (start+end)//2

        build_rest_point = count(mid)

        if(build_rest_point>M):
                start = mid+1
        else:
            end = mid-1
    print(start)



