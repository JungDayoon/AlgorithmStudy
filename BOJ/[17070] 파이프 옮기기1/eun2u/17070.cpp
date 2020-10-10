#include <iostream>
#include <utility>
#include <cstring>
#include <algorithm>
#include <queue>
#include <vector>
using namespace std;
int N;
int house[16][16];
int dy[3]={0,1,1}; //우, 대각선. 하. 
int dx[3]={1,1,0}; //0은 0,1 가능. 1은 0,1,2, 가능. 2는 1,2 가능 
int pos[3][3]={    {0,1},{0,1,2},{1,2}  };
int result=0;
//vector< pair<int,int> > pipes;

bool isSpace(int dir, int y, int x){
    if(dir==0 || dir==2){
        if(house[y][x]==1) return false;
    }
    else if(dir==1){
        if(house[y][x]==1) return false;
        if(house[y-1][x]==1) return false;
        if(house[y][x-1]==1) return false;
    }
    return true;
}
bool inRange(int y, int x){
    if(y<0 || y>=N) return false;
    if(x<0 || x>=N) return false;
    return true;
}
void movePipes(int y1, int x1, int y2, int x2,int shape){

    if(y2==N-1 && x2==N-1){
        result++;

        // for(int i=0;i<pipes.size();i++)
        //     cout<<pipes[i].first<<" "<< pipes[i].second<< ", ";
        // cout<<"\n";
         return;
    }

    int psize;
    if(shape==0 || shape==2) psize=2;
    else psize=3;
    for(int i=0;i<psize;i++){
        int dir=pos[shape][i];
        int ny=y2+dy[pos[shape][i]];
        int nx=x2+dx[pos[shape][i]];

        if(!inRange(ny,nx)) continue;
        if(isSpace(dir,ny,nx)){
           // pipes.push_back(make_pair(ny,nx));
            movePipes(y2,x2,ny,nx,dir);
           // pipes.pop_back();
        }
    }
}
int main(){

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin>>N;
    for(int i=0;i<N;i++)
        for(int j=0;j<N;j++)
            cin>>house[i][j];

    movePipes(0,0,0,1,0);

    cout<<result<<'\n';

    return 0;
}