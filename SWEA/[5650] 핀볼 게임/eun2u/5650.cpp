#include <iostream>
#include <vector>
#include <cstring>
#include <utility>
#include <algorithm>

using namespace std;
int N;
int map[101][101];
int dy[4]={0,1,0,-1};
int dx[4]={1,0,-1,0};
int blockdir[6][4]={{-1,-1,-1,-1},
    {2,0,3,1},{2,3,1,0},{1,3,0,2},{3,2,0,1},{2,3,0,1}
};
vector< pair<int,int> > wormhall[11];

bool inRange(int y, int x){
    if(y<0 || y>N) return false;
    if(x<0 || x>N) return false;
    return true;
}
int reverseDir(int dir){
    return ((dir+2)%4);
}
int startBall(int y, int x,int dir){
    int ret=0;
    int sy,sx;
    sy=y;
    sx=x;

    while(1){
        int ny=y+dy[dir];
        int nx=x+dx[dir];
        
        if(!inRange(ny, nx)){
            ret++;
            dir=reverseDir(dir);
            y=ny,x=nx;
            continue;
        }
        int bnum=map[ny][nx];
        if(sy==ny && sx==nx) break;
        if(bnum==-1) break;

        if(bnum>=1 && bnum<=5){
            dir=blockdir[bnum][dir];
            ret++;
        }
        else if(bnum>=6 && bnum<=10){
            int idx;
            if(wormhall[bnum][0].first==ny && wormhall[bnum][0].second==nx)
                idx=1;
            else idx=0;
            
            ny=wormhall[bnum][idx].first;
            nx=wormhall[bnum][idx].second;
        }

        y=ny, x=nx;
    }
    return ret;
}
int main(){
    int TC;
    cin>>TC;
    for(int tc=1;tc<=TC;tc++){
        cin>>N;
        memset(map,0,sizeof(map));
        for(int i=0;i<11;i++)
            wormhall[i].clear();

        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++){
                cin>>map[i][j];
                
                if(map[i][j]>=6 || map[i][j]<=10)
                    wormhall[map[i][j]].push_back(make_pair(i,j));
            }

        int ans=0;
        for(int y=0;y<N;y++){
            for(int x=0;x<N;x++){
                if(map[y][x]==0){
                    for(int dir=0;dir<4;dir++){
                        int result=startBall(y,x,dir);
                        //cout<<"y: "<<y<<" x: "<<x<<" dir: "<<dir<<" result : "<<result<<'\n';
                        ans=max(ans,result);
                    }   
                }
            }
        }
        cout<<"#"<<tc<<" "<<ans<<'\n';
    }
    return 0;
}