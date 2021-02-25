#include <iostream>
#include <cstring>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;
int N;
int dp[100005][3];
int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin>>N;
    
    dp[1][0]=1;
    dp[1][1]=1;
    dp[1][2]=1;

    for(int i=2;i<=N;i++){
        dp[i][0]=(dp[i-1][0]+dp[i-1][1]+dp[i-1][2])%9901;
        dp[i][1]=(dp[i-1][0]+dp[i-1][2])%9901;
        dp[i][2]=(dp[i-1][0]+dp[i-1][1])%9901;
    }
    int result=dp[N][0]+dp[N][1]+dp[N][2];
    cout<<result%9901<<'\n';

    return 0;
}