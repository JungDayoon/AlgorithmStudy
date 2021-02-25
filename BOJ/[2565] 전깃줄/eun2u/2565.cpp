#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
vector<pair<int,int> > elec;

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    int N,a,b,LIS=0;
    cin>>N;
    for(int i=0;i<N;i++){
        cin>>a>>b;
        elec.push_back(make_pair(a,b));
    }

    sort(elec.begin(), elec.end());
    vector<int> dp(N,1);

    for(int i=0;i<N;i++){
        for(int j=0;j<i;j++){
            if(elec[i].first > elec[j].first && elec[i].second > elec[j].second)
                dp[i]=max(dp[i], dp[j]+1);
        }
        LIS=max(LIS, dp[i]);
    }
    
    cout<<N-LIS<<'\n';

    return 0;
}