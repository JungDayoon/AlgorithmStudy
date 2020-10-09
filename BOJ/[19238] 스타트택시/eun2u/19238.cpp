#include <iostream>
#include <utility>
#include <algorithm>
#include <string>
#include <cstring>
#include <queue>

using namespace std;
int N,M,Fuel;
int board[20][20];
int dy[4] = {0, 1, 0, -1};
int dx[4] ={1, 0, -1, -0};
pair<int, int> taxi;
struct P{
    int sy,sx,dy,dx;
};
vector< P > people;
int distances[20][20];

bool inRange(int y,int x){
    if(y<0 || y>=N)   return false;
    if(x<0 || x>=N)   return false;
    return true;
}
void shortestDist(int ty,int tx){
    queue< pair<int,int> > q;
    fill(distances[0],distances[20],-1);
    distances[ty][tx]=0;
    q.push(make_pair(ty,tx));

    while(!q.empty()){
        pair<int,int> here=q.front();
        q.pop();

        for(int dir=0;dir<4;dir++){
            int ny=here.first+dy[dir];
            int nx=here.second+dx[dir];

            if(!inRange(ny,nx))
                continue;
            if(distances[ny][nx]==-1 && board[ny][nx]==0){
                q.push(make_pair(ny,nx));
                distances[ny][nx]=distances[here.first][here.second]+1;
            }
        }
    }
}
void printandExit(){
    cout<<-1<<'\n';
    exit(0);
}
bool cmp_Dist(pair<P,int> a, pair<P, int> b) { 
    if(a.second==b.second){
        if(a.first.sy==b.first.sy)
            return a.first.sx<b.first.sx;
        else 
            return a.first.sy< b.first.sy;
    }
    else return a.second<b.second;
}
int main(){

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin>> N>>M>>Fuel;
    for(int i=0;i<N;i++)
        for(int j=0;j<N;j++)
            cin>>board[i][j];

    cin >>taxi.first>>taxi.second;
    taxi.first-=1;
    taxi.second-=1;
    int sy,sx,dy,dx;
    for(int i=0;i<M;i++){
        cin>>sy>>sx>>dy>>dx;
        P input;
        input.sy=sy-1;
        input.sx=sx-1;
        input.dy=dy-1;
        input.dx=dx-1;
        people.push_back(input);
    }

    vector< pair<P,int> > dists;
    int psize=M;
    while(psize>0){
        shortestDist(taxi.first,taxi.second);
        dists.clear();
        for(int i=0;i<psize;i++){
            P p=people[i];
            dists.push_back(make_pair(p,distances[people[i].sy][people[i].sx]));
        }

        sort(dists.begin(),dists.end(),cmp_Dist);

        if(dists[0].second<0)
            printandExit();
        Fuel-=dists[0].second;
        if(Fuel<0)
            printandExit();
       // cout<<Fuel<<'\n';

        shortestDist(dists[0].first.sy, dists[0].first.sx);
        int boardingTime=distances[dists[0].first.dy][dists[0].first.dx];
        Fuel-=boardingTime;
        if(Fuel<0)
            printandExit();
        Fuel+=boardingTime*2;
       // cout<<Fuel<<'\n';
        people.clear();
        for(int i=1;i<psize;i++)
            people.push_back(dists[i].first);
        taxi.first=dists[0].first.dy;
        taxi.second=dists[0].first.dx;

        psize--;
    }
    cout<<Fuel <<'\n';

}