#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#include <algorithm>
using namespace std;

int N,M;
int map[1001][1001];
int dist[1001][1001][2];
int dy[4]={0,1,0,-1};
int dx[4]={1,0,-1,0};
struct go{
    int y,x,w;
};
bool inRange(int y, int x){
    if(y<0 || y>=N) return false;
    if(x<0 || x>=M) return false;
    return true;
}
void bfs(int sy,int sx){

    memset(dist,0,sizeof(dist));
    queue<go> q;
    go g={sy,sx,0};
    q.push(g);
    dist[sy][sx][0]=1;
    dist[sy][sx][1]=-1;
    

    while(!q.empty()){
        go here=q.front();
        q.pop();

        for(int dir=0;dir<4;dir++){
            int ny=here.y+dy[dir];
            int nx=here.x+dx[dir];
            int wcnt=here.w;
            
            if(!inRange(ny,nx)) continue;
            
            if(dist[ny][nx][wcnt]==0){    
                if(map[ny][nx]==0){
                    dist[ny][nx][wcnt]=dist[here.y][here.x][wcnt]+1;
                    go g={ny,nx,wcnt};
                    q.push(g);
                }
                else if(map[ny][nx]==1 && wcnt==0){
                    dist[ny][nx][wcnt+1]=dist[here.y][here.x][wcnt]+1;
                    go g={ny,nx,wcnt+1};
                    q.push(g);
                }
            }
        }
    }   
}

int main(){
    
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin>>N>>M;
    string s;
    for(int i=0;i<N;i++){
        cin>>s;
        for(int j=0;j<M;j++){
            map[i][j]=s[j]-'0';
        }
    }

    bfs(0,0);

    int ans0=dist[N-1][M-1][0];
    int ans1=dist[N-1][M-1][1];
    int result=0;
    if(ans0>0 && ans1>0)
        result=min(ans0,ans1);
    else
        result=max(ans0,ans1);
    result=result==0?-1:result;

    cout<< result <<'\n';
       
    return 0;
}