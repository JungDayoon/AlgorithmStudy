#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;
long long N,k;
const long long MAX_VAL=10000000000;

bool binarySearch(int mid){
    long long cnt=0;

    for(int i=1;i<=N;i++){
        for(int j=1;j<=N;j++){
            if(i*j <= mid)
                cnt++;
        }
    }

    if(cnt< k)
        return false;
    else return true;
}
int main(){

    cin>>N>>k;

    long long low=0, high=min(MAX_VAL,N*N);
    long long result;

    while(low<=high){
        int mid=(low+high)/2;

        if(binarySearch(mid)){//cnt >= k
            result=mid;
            high=mid-1;
        }
        else
            low=mid+1;
    }
    cout<<result;
    
    return 0;
}