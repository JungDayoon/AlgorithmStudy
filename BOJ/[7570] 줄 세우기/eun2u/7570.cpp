#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;
int main(){
    ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    int N,result=0;
    cin>>N;
    vector<int> arr(N+1);
    for(int i=0;i<N;i++)
        cin>>arr[i];

    vector<int> dp(N+1);
    dp[arr[0]]=1;
    for(int i=1;i<N;i++){
        dp[arr[i]]=dp[arr[i]-1]+1;
        result=max(result,dp[arr[i]]);
    }
    cout<<N-result<<'\n';
    
    return 0;
}