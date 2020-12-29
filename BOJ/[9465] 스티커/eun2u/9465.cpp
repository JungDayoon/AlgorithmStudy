#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;

int pic[2][100000];
int findMaxScore(int n){
    int score[2][100000];

    score[0][0]=pic[0][0];
    score[1][0]=pic[1][0];

    for(int i=1;i<n;i++){
        score[0][i]=max(score[1][i-1]+pic[0][i], score[0][i-1]);
        score[1][i]=max(score[0][i-1]+pic[1][i], score[1][i-1]);
    }
    
    return max(score[0][n-1],score[1][n-1]);
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int TC;
    cin>>TC;
    for(int tc=0;tc<TC;tc++){
        int n;
        cin>>n;
        
        memset(pic, 0, sizeof(pic));

        for(int i=0;i<2;i++)   
            for(int j=0;j<n;j++)
                cin>>pic[i][j];
        
        int result=findMaxScore(n);
        cout<<result<<'\n';
    }
    return 0;
}