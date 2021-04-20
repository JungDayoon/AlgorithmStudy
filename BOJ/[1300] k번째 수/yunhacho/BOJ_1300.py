def KthNumber(N, k):
    result=0
    left=1; right=k

    while(left<=right):
        cnt=0
        mid=(left+right)//2 
        for i in range(1,N+1):
            cnt+=min(mid//i, N)

        if(cnt<k): left=mid+1
        else:
            result=mid
            right=mid-1

    return result

if __name__=="__main__":
    N=int(input())
    k=int(input())
    print(KthNumber(N,k))
    