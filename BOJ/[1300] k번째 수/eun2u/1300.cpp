#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;
long long N,k;
const long long MAX_VAL=10000000000;

int binarySearch(long long mid){
    long long cnt=0;

    for(int i=1;i<=N;i++){
        cnt+=min(mid/i,N);
        // for(int j=1;j<=N;j++){
        //     if(i*j <= mid)
        //         cnt++;
        // }
    }

    return cnt;
}
int main(){

    cin>>N>>k;

    long long low=0, high=min(MAX_VAL,N*N);
    long long result=1;

    while(low<=high){
        int mid=(low+high)/2;

        if(binarySearch(mid) >= k){
            result=mid;
            high=mid-1;
        }
        else
            low=mid+1;
    }
    cout<<result;
    
    return 0;
}