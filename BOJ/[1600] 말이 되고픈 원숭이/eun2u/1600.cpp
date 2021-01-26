#include <iostream>
#include <utility>
#include <algorithm>
#include <cstring>
#include <queue>
using namespace std;
const int INF=987654321;
int K,W,H;
int map[201][201];
int dist[201][201][31];

int dy[12]={-2,-1,1,2,2,1,-1,-2,0,1,0,-1};
int dx[12]={1,2,2,1,-1,-2,-2,-1,1,0,-1,0};

struct loc{
    int y,x,h;
};

bool inRange(int y, int x){
    if(y<0 || y>=H) return false;
    if(x<0 || x>=W) return false;
    return true;
}
void moveMonkey(){
    queue<loc > q;
    memset(dist,-1,sizeof(dist));
    loc l={0,0,0};
    q.push(l);
    dist[0][0][0]=0;
    
    while(!q.empty()){
        loc here=q.front();
        q.pop();

        for(int dir=0;dir<12;dir++){
            int ny=here.y+dy[dir];
            int nx=here.x+dx[dir];
            int hmove=here.h;
            if(!inRange(ny, nx)) continue;
            if(map[ny][nx]==1) continue;

            if(dir<8 && here.h+1 > K) {//말이 움직이고 K 번 다쓴 경우
                dir=7;
                continue;
            }
            if(dir<8){ //말이 움직임
                if(dist[ny][nx][hmove+1]==-1){
                    dist[ny][nx][hmove+1]=dist[here.y][here.x][hmove]+1;
                    loc l={ny,nx,hmove+1};
                    q.push(l);
                }
            }
            else if(dir>=8){// 원숭이가 움직임
                if(dist[ny][nx][hmove]==-1){
                    dist[ny][nx][hmove]=dist[here.y][here.x][hmove]+1;
                    loc l={ny,nx,hmove};
                    q.push(l);
                }
            }
        }
    }
}
int findMinStep(){
    int ret=INF;
    for(int i=0;i<=K;i++){
        if(dist[H-1][W-1][i]!=-1)
            ret=min(ret,dist[H-1][W-1][i]);
    }
    return ret==INF?-1:ret;
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin>>K>>W>>H;

    for(int i=0;i<H;i++)
        for(int j=0;j<W;j++)
            cin>>map[i][j];

    moveMonkey();
    int result=findMinStep();
    cout<<result<<'\n';
    return 0;
}