#include <iostream>
#include <utility>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;
int dy[4]={0,1,0,-1};
int dx[4]={1,0,-1,0};
char map[6][6];
bool discoverd[6][6];
int result=0;
vector<int> picked;

bool inRange(int y, int x){
    if(y<0 || y>=5) return false;
    if(x<0 || x>=5) return false;
    return true;
}
bool checkS(vector<int> picked){

    int cntS=0;
    for(int i=0;i<picked.size();i++){
        int idx=picked[i];
        int y=idx/5;
        int x=idx-(y*5);

        if(map[y][x]=='S')
            cntS++;
    }
    if(cntS>=4) return true;
    else return false;
}
void dfs(int y, int x){
    discoverd[y][x]=true;

    for(int dir=0;dir<4;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];

        if(!inRange(ny,nx)) continue;
        if(!discoverd[ny][nx])
            dfs(ny,nx);
    }
}
bool coveredAll(){
    for(int i=0;i<5;i++)
        for(int j=0;j<5;j++)
            if(!discoverd[i][j])
                return false;
    return true;
}
bool isAdjacent(vector<int> picked){

    memset(discoverd,true,sizeof(discoverd));
    for(int i=0;i<picked.size();i++){
        int idx=picked[i];
        int y=idx/5;
        int x=idx-(y*5);
        discoverd[y][x]=false;
    }

    int y=picked[0]/5;
    int x=picked[0]-(y*5);
    dfs(y,x);

    if(coveredAll()) return true;
    else return false;
}

void comb(int cnt){
    if(cnt==7){
        if(checkS(picked)){
            if(isAdjacent(picked))
                result++;
        }
        return ;
    }

    int smallest=picked.empty() ? 0 :picked.back()+1;

    for(int next=smallest;next<25;next++){
        picked.push_back(next);
        comb(cnt+1);
        picked.pop_back();
    }
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    for(int i=0;i<5;i++)
        for(int j=0;j<5;j++)
            cin>>map[i][j];

    comb(0);

    cout<<result<<'\n';
    
    return 0;
}