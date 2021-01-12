#include <string>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int dy[4]={1,-1,0,0};
int dx[4]={0,0,1,-1};
vector<int> adj[1111];
int direction(char dir){
    if(dir=='U')
        return 0;
    else if(dir=='D')
        return 1;
    else if(dir=='R')
        return 2;
    else return 3;
}
bool inRange(int y, int x){
    if(y>10 || y<0) return false;
    if(x>10 || x<0) return false;
    return true;
}
int solution(string dirs) {
    int answer = 0;
    int sy=5,sx=5;
    
    for(int i=0;i<dirs.size();i++){
        int dir=direction(dirs[i]);
           
        int ny=sy+dy[dir];
        int nx=sx+dx[dir];
        
        if(!inRange(ny,nx)) continue;
        int a=sx*100+sy;
        int b=nx*100+ny;
        
        vector<int>::iterator iter;
        iter=find(adj[a].begin(), adj[a].end(),b);
        if(iter==adj[a].end()){
            answer++;
            adj[a].push_back(b);
            adj[b].push_back(a);
        }
        sy=ny;
        sx=nx;
    }
    
    return answer;
}