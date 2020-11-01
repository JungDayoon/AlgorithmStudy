#include <iostream>
#include <unordered_map>
#include <utility>
using namespace std;
int N,M;
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin>>N;
    unordered_map<int,int> m;
    int num;
    for(int i=0;i<N;i++){
        cin>>num;
        m[num]++;
    }
    cin>>M;
    for(int i=0;i<M;i++){
        cin>>num;
        cout<<m[num]<<" ";
    }
    return 0;
}