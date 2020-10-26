//[15684] 사다리 조작
#include <iostream>
#include <utility>
#include <cstring>
#include <algorithm>
#include <vector>
using namespace std;

const int INF=987654321;
int result=INF;
int N,M,H; //N은 가로 H는 세로
int map[40][20];

bool checkCanGo(){
    for(int j=1;j<=N;j++){
        int y=1, x=j;

        while(y<=H){
            if(map[y][x]==1){
                x+=1, y+=1;
            }
            else if(map[y][x-1]==1){
                x-=1, y+=1;
            }
            else y+=1;
        }
        if(x!=j) 
            return false;
    }
    return true;
}
void putLadder( int toPick,int cnt,int y){
    if(result!=INF) return;
    if(toPick==cnt){
        if(checkCanGo())
            result=cnt;
        
        return;
    }

    for(int i=y;i<=H;i++){
        for(int j=1;j<N;j++){
            if(map[i][j]==1 || map[i][j-1]==1 || map[i][j+1]==1) 
                continue;

            map[i][j]=1;
            putLadder(toPick,cnt+1,i);
            map[i][j]=0;    
        }
    }
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin>>N>>M>>H;
    int a,b;
    for(int i=0;i<M;i++){
        cin>>a>>b;
        map[a][b]=1;
    }

    for(int i=0;i<=3;i++){
        putLadder(i,0,1);
        if(result!=INF) break;
    }

    if(result==INF)
        cout<<"-1"<<'\n';
    else cout<<result<<'\n';

    return 0;
}