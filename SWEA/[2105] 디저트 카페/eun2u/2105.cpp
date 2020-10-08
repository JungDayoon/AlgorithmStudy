#include <iostream>
#include <algorithm>
#include <utility>
#include <string>
#include <queue>
#include <cstring>
using namespace std;
int N;
int board[20][20];
int dy[4]={1,1,-1,-1};
int dx[4]={1,-1,-1,1};
bool eaten[101];
bool visited[20][20];
int result=0;
int startY,startX=0;

bool isPoint(int y, int x){
    if(x==0 && y==0) return true;
    if(x==N-1 && y==N-1) return true;
    return false;
}
bool inRange(int y,int x){
    if(y<0 || y>=N) return false;
    if(x<0 || x>=N) return false;
    return true;
}
void dfs(int y,int x,int dir, int picked){
    if(startY==y && startX==x && picked>0){
        result=max(result,picked);
        return;
    }
    
    for(int i=0;i<2;i++){
        int ny=y+dy[dir+i];
        int nx=x+dx[dir+i];

        if(!inRange(ny,nx) || eaten[board[ny][nx]])
            continue;
        
        if(!visited[ny][nx]){
            visited[ny][nx]=true;
            eaten[board[ny][nx]]=true;
            dfs(ny,nx,dir+i,picked+1);
            visited[ny][nx]=false;
            eaten[board[ny][nx]]=false;
        }
    }

}
int main(){
    int TC;
    cin>>TC;
    for(int tc=1;tc<=TC;tc++){
        cin>>N;
        result=0;
        memset(eaten,false, sizeof(eaten));
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++){
                cin>>board[i][j];
                visited[i][j]=false;
            }
        
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!isPoint(i,j)){
                    startY=i;
                    startX=j;
                    dfs(i,j,0,0);
                }
            }
        }
        if(result==0)
            cout<<"#"<<tc<<" "<<-1<<'\n';
        else
            cout<<"#"<<tc<<" "<<result<<'\n';
    }

    return 0;
}