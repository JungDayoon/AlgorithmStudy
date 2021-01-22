#include <iostream>
#include <cstring>
#include <utility>
#include <algorithm>
using namespace std;
int M,N;
int map[501][501];
int dist[501][501];
bool visited[501][501];
int dy[4]={0,-1,0,1};
int dx[4]={-1,0,1,0};

bool inRange(int y, int x){
    if(y<0 || y>=M) return false;
    if(x<0 || x>=N) return false;
    return true;
}
int casesCanGo(int y, int x){
    visited[y][x]=true;
    for(int dir=0;dir<4;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];

        if(!inRange(ny,nx)) continue;

        if(map[y][x] < map[ny][nx]){
            if(!visited[ny][nx])
                dist[y][x]+=casesCanGo(ny,nx);
            else dist[y][x]+=dist[ny][nx];
        }
    }
    return dist[y][x];
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin>>M>>N;

    for(int i=0;i<M;i++)
        for(int j=0;j<N;j++)
            cin>>map[i][j];

    dist[0][0]=1;
    visited[0][0]=true;
    

    for(int y=0;y<M;y++){
        for(int x=0;x<N;x++){
            if(visited[y][x]) continue; //갔던 길이면 continue

            for(int dir=0;dir<4;dir++){
                int ny=y+dy[dir];
                int nx=x+dx[dir];

                if(!inRange(ny,nx)) continue;

                if(map[y][x] < map[ny][nx]){
                    if(!visited[ny][nx])   
                        dist[ny][nx]=casesCanGo(ny,nx);

                    dist[y][x]+=dist[ny][nx];
                    visited[y][x]=true;
                }
            }
        }
    }    

   // print();
    cout<< dist[M-1][N-1]<<'\n';
    return 0;
}