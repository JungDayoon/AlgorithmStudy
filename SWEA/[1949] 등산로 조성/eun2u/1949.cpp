#include <iostream>
#include <utility>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N,K;
int map[8][8];
bool visited[8][8];
int result, maxHeight,nowk;
int preVal;
vector< pair<int,int> > point;
int dy[4]={0,1,0,-1};
int dx[4]={1,0,-1,0};

bool inRange(int y,int x){
    if(y<0 || y>=N) return false;
    if(x<0 || x>=N) return false;
    return true;
}
void makeRoad(int y, int x,int cnt){
    //종료조건.. 상하좌우 모두 갈 수 없으면 종료
    int flag=0;
    for(int dir=0;dir<4;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];
        if(!inRange(ny,nx) || visited[ny][nx] || map[y][x] <= map[ny][nx]-nowk) continue;
        flag=1;
    }
    if(flag==0){
        result=max(result, cnt);
        return;
    }
    

    for(int dir=0;dir<4;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];

        if(!inRange(ny,nx)) continue;

        if(!visited[ny][nx]){
            if(map[ny][nx] < map[y][x]){
                visited[ny][nx]=true;
                makeRoad(ny,nx,cnt+1);
                visited[ny][nx]=false;
            }
            else{
                if(nowk>0 && (map[ny][nx]-nowk < map[y][x]) ){//nowk가 존재하고, 깍아서 높이가 낮아지면
                    int diff=map[ny][nx]-map[y][x];
                    preVal=map[ny][nx];

                    map[ny][nx]-=diff+1;
                    visited[ny][nx]=true;
                    nowk=0;
                    makeRoad(ny,nx, cnt+1);
                    map[ny][nx]=preVal;
                    visited[ny][nx]=false;
                    nowk=K;
                }
            }
        }
    }
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int TC;
    cin>>TC;
    for(int tc=1;tc<=TC;tc++){
        cin>>N>>K;
        memset(map,0,sizeof(map));
        maxHeight=0;
        result=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                cin>>map[i][j];
                maxHeight=max(map[i][j],maxHeight);
            }
        }
        point.clear();
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                if(map[i][j]==maxHeight)
                    point.push_back(make_pair(i,j));
        

        for(int i=0;i<point.size();i++){
            memset(visited,false,sizeof(visited));
            nowk=K;
            preVal=0;
            visited[point[i].first][point[i].second]=true;
            makeRoad(point[i].first,point[i].second,1);
        }

        cout<<"#"<<tc<<" "<<result<<'\n';
    }
    return 0;
}