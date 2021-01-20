#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;
int N;
int map[101][101];
int dy[4]={0,1,0,-1};
int dx[4]={1,0,-1,0};
vector< pair<int,int> > warmhall[11];

bool inRange(int y, int x){
    if(y<0 || y>N) return false;
    if(x<0 || x>N) return false;
    return true;
}
int reverseDir(int dir){
    return ((dir+2)%4);
}
int blockDir(int b, int dir){
    if(b==1){
        if(dir==1) return 0;
        else if(dir==2) return 3;
        else return reverseDir(dir);
    }
    else if(b==2){
        if(dir==3) return 0;
        else if(dir==2) return 1;
        else return reverseDir(dir);
    }
    else if(b==3){
        if(dir==0) return 1;
        else if(dir==3) return 2;
        else return reverseDir(dir);
    }
    if(b==4){
        if(dir==0) return 3;
        else if(dir==1) return 2;
        else return reverseDir(dir);
    }
    else if(b==5)
        return reverseDir(dir);
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
            dir=blockDir(bnum,dir);
            ret++;
        }
        else if(bnum>=6 && bnum<=10){
            int idx;
            if(warmhall[bnum][0].first==ny && warmhall[bnum][0].second==nx)
                idx=1;
            else idx=0;
            
            ny=warmhall[bnum][idx].first;
            nx=warmhall[bnum][idx].second;
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
            warmhall[i].clear();

        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++){
                cin>>map[i][j];
                
                if(map[i][j]>=6 || map[i][j]<=10)
                    warmhall[map[i][j]].push_back(make_pair(i,j));
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