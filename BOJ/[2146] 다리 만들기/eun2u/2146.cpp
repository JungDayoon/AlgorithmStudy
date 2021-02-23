#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N;
int map[105][105];
bool visited[105][105];
vector<vector<pair<int,int> > > island(10005);
vector<int> picked;
int result=987654321;
int dy[4]={0,1,0,-1};
int dx[4]={1,0,-1,0};

bool inRange(int y, int x){
    if(y<0 || y>=N) return false;
    if(x<0 || x>=N) return false;
    return true;
}
bool adjOcean(int y, int x){
    for(int dir=0;dir<4;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];
        if(!inRange(ny,nx)) continue;
        if(map[ny][nx]==0) 
            return true;
    }
    return false;
}
void dfs(int y, int x,int idx){
    
    visited[y][x]=true;
    if(adjOcean(y,x))
        island[idx].push_back(make_pair(y,x));
        
    for(int dir=0;dir<4;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];
        if(!inRange(ny,nx)) continue;
        if(!visited[ny][nx] && map[ny][nx]==1) 
            dfs(ny,nx,idx);
    }
}
void findMinDist(vector<int> picked){
    for(auto item1 : island[picked[0]]){
        for(auto item2 : island[picked[1]]){
            int len=abs(item1.first-item2.first) + abs(item1.second-item2.second)-1;
            result = min(result,len);
        }
    }
}
void comb(int cnt,int num){
    if(cnt==2){
        findMinDist(picked);
        return;
    }
    int smallest=picked.empty()?1:picked.back()+1;

    for(int next=smallest;next<num;next++){
        picked.push_back(next);
        comb(cnt+1, num);
        picked.pop_back();
    }
}
int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin>>N;
    for(int i=0;i<N;i++)   
        for(int j=0;j<N;j++)
            cin>>map[i][j];
    

    int num=1;
    for(int i=0;i<N;i++)
        for(int j=0;j<N;j++)
            if(!visited[i][j] && map[i][j]==1)
                dfs(i,j,num++);

    comb(0,num);
    cout<<result<<'\n';
    return 0;
}