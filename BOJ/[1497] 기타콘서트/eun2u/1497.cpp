#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <string>
#include <algorithm>
#include <bitset>
using namespace std;
int N,M;
long long guitar[11];
int maxSong,result;

int countSong(long long snum){
    bitset<50> ret(snum);
    return ret.count();
}
void playGuitar(int idx, int gcnt, long long songs){
    int scnt=countSong(songs);
    if(scnt > maxSong){
        maxSong=scnt;
        result=gcnt;
    }
    else if(scnt==maxSong){
        result=min(result,gcnt);
    }
    if(idx>=N) return;

    playGuitar(idx+1, gcnt+1, songs|guitar[idx]);
    playGuitar(idx+1, gcnt, songs);
}
int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin>>N>>M;
    string s,plays;
    for(int i=0;i<N;i++){
        cin>>s>>plays;
        for(int j=0;j<plays.size();j++){
            if(plays[j]=='Y'){
                guitar[i]|=(1LL <<M-j-1); //1LL은 longlong에서 1
            }
        }
    }
    
    playGuitar(0,0,0);
    result = result==0?-1:result;
    cout<<result<<'\n';
    return 0;
}