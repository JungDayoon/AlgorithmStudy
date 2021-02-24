#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int N;
int dp[31];
int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin>>N;
    if((N*3)%2 != 0){
        cout<<0<<'\n';
        return 0;
    }
    dp[0]=1;
    dp[2]=3;
    for(int i=4;i<=N;i++){
        dp[i]=dp[i-2]*dp[2];
        for (int j = i - 4; j >= 0; j-=2)
            dp[i] += dp[j]*2;
        
    }
    cout<<dp[N]<<'\n';


    return 0;
}