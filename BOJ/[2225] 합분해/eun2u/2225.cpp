#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;

int N,K;
long long dp[201][201];
int main(){

    cin>>N>>K;

    for(int n=0;n<N+1;n++)
        dp[1][n]=1;
    

    for(int k=2;k<K+1;k++){
        for(int n=0;n<N+1;n++){
            long long sum=0;
            for(int i=0;i<n+1;i++){
                sum+=dp[k-1][i];
            }
            dp[k][n]=sum%1000000000;
        }
    }
    cout<<dp[K][N]<<'\n';

    return 0;
}