#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N;
double result;
double probs[4];
bool visited[31][31];
int dy[4]={0,0,1,-1};
int dx[4]={1,-1,0,0};

void crazyRobot(int sy, int sx, int cnt,double p){
    if(cnt==N){
        result+=p;
        return;
    }
    for(int dir=0;dir<4;dir++){
        int ny=sy+dy[dir];
        int nx=sx+dx[dir];

        if(!visited[ny][nx]) {
            visited[ny][nx]=true;
            crazyRobot(ny,nx,cnt+1,p*probs[dir]);
            visited[ny][nx]=false;
        }
    }
}
int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cout.precision(11);
    
    cin>>N>>probs[0]>>probs[1]>>probs[2]>>probs[3];
    for(int i=0;i<4;i++)
        probs[i]/=100;

    visited[15][15]=true;
    double p=1;
    crazyRobot(15,15,0,p);
    cout<<result<<'\n';

    return 0;
}