#include <iostream>
#include <utility>
#include <string>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N,M,L;
int map[50][50];
bool visited[50][50];
struct Q{
        int y,x,time;
};
int dy[4]={0,1,0,-1};
int dx[4]={1,0,-1,0};
int con[8][4]={    {-1},    {0,1,2,3},    {1,3},    {0,2},
    {0,3},    {0,1},    {1,2},    {2,3}};


bool inRange(int y,int x){
    if(y<0 || y>=N) return false;
    if(x<0 || x>=M) return false;  
    return true;
}
bool isConnected(int dir, int nTunnel){
    int tsize;
    if(nTunnel==1) tsize=4;
    else if(nTunnel>=1 && nTunnel<=7) tsize=2;
    else return false;

    for(int i=0;i<tsize;i++){ //0-2 연결, 1-3 연결
        if(dir==0 && con[nTunnel][i] ==2)
            return true;
        if(dir==1 && con[nTunnel][i] ==3)
            return true;
        if(dir==2 && con[nTunnel][i] ==0)
            return true;
        if(dir==3 && con[nTunnel][i] ==1)
            return true;
    }
    return false;
}
int bfs(int r, int c){

    int result=1;
    queue< Q > q;
    memset(visited,false,sizeof(visited));
    visited[r][c]=true;
    Q input;
    input.y=r;
    input.x=c;
    input.time=1;
    q.push(input);

    while(!q.empty()){ 
        Q here=q.front();
        q.pop();

        int tunnel=map[here.y][here.x];
        int tsize;
        if(tunnel==1)    tsize=4;
        else if(tunnel>=2 && tunnel<=7)
            tsize=2;

        for(int dir=0;dir<tsize;dir++){
            int cango=con[tunnel][dir];
            int ny=here.y+dy[cango];
            int nx=here.x+dx[cango];

            if(here.time==L)
                return result;
            
            if(!inRange(ny,nx))
                continue;

            if(isConnected(cango,map[ny][nx]) && !visited[ny][nx] && map[ny][nx]!=0){ 
                Q input;
                input.y=ny;
                input.x=nx;
                input.time=here.time+1;
                q.push(input);
                visited[ny][nx]=true;
              //  cout<<ny<<" "<<nx<<" "<<input.time+1<<"\n";
                result++;
            }
        }
    }
    return result;
}
int main(){
    int TC,R,C;
    cin>>TC;

    for(int i=1;i<=TC;i++){
        cin>>N>>M>>R>>C>>L;
        memset(map,0,sizeof(map));
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++)
                cin>>map[i][j];
        int ret=bfs(R,C);
        cout<<"#"<<i<<" "<< ret<<"\n";

    }
    return 0;
}