#include <iostream>
#include <utility>
#include <cstring>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;
int N;

int dy[4]= {0,-1,0,1};
int dx[4] ={1,0,-1,0};
bool map[101][101];

bool inRange(int y, int x){
    if(y<0 || y>100) return false;
    if(x<0 || x> 100) return false;
    return true;
}
void dragonCurve(int y, int x, int d, int g){

    vector<int> dirs;
    dirs.push_back(d);
    int ny=y+dy[d];
    int nx=x+dx[d];
    map[ny][nx]=true;
    if(g==0) return;
    y=ny;
    x=nx;

    for(int nowg=1;nowg<=g;nowg++){
        for(int i=pow(2,nowg-1)-1;i>=0;i--){ //2^nowg-1
            int rdir=(dirs[i]+1)%4;
            dirs.push_back(rdir);           

            ny=y+dy[rdir];
            nx=x+dx[rdir];

            if(inRange(ny,nx)) {
                map[ny][nx]=true;
            
                y=ny;
                x=nx;
            }  
        }
    }  
}
int calculSquare(){
    int ret=0;
    for(int i=0;i<100;i++)
        for(int j=0;j<100;j++)
            if(map[i][j]==true && map[i][j+1]==true && map[i+1][j]==true && map[i+1][j+1]==true)
                ret++;
    
    return ret;
    
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin>>N;
    int y,x,d,g;
    for(int i=0;i<N;i++){
        cin>> x >>y>>d>>g;
        map[y][x]=true;
        dragonCurve(y,x,d,g);
    }
    cout<<calculSquare()<<'\n';
    return 0;
}