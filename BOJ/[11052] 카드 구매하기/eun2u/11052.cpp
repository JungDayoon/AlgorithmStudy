#include <iostream>
#include <cstring>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;

int n;
int findMaxP(vector<int> cards){
    vector<int> P(n+1,0);
    P[1]=cards[1];

    for(int i=2;i<=n;i++){
        int maxVal=cards[i];
        for(int j=1;j<=i/2;j++)
            maxVal=max(maxVal,P[j]+P[i-j]);
        
        P[i]=maxVal;
    }
    return P[n];
}
int main(){
    cin>>n;
    vector<int> cards(n+1);
    
    for(int i=1;i<=n;i++)
        cin>>cards[i];
    
    int result=findMaxP(cards);
    cout<<result<<'\n';

    return 0;
}