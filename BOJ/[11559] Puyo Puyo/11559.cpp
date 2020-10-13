#include <iostream>
#include <utility>
#include <string>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int board[12][6];
bool visited[12][6];
bool isPuyo[12][6];
int dy[4]={0,1,0,-1};
int dx[4]={1,0,-1,0};

void dropPuyo(){

    for(int i=0;i<6;i++){
        vector<int> line;
        for(int j=11;j>=0;j--){
            if(board[j][i])
                line.push_back(board[j][i]);
        }
        int len=line.size();
        if(len>0){
            for(int j=0;j<len;j++)
                board[11-j][i]=line[j];
            
            for(int j=len;j<12;j++)
                board[11-j][i]=0;
        }
    }
}
bool inRange(int y,int x){
    if(y<0 || y>=12) return false;
    if(x<0 || x>=6) return false;
    return true;
}
void bfs(int y,int x){
    queue< pair<int,int> > q;
    vector< pair<int,int> > puyo;
    puyo.push_back(make_pair(y,x));
    q.push(make_pair(y,x));
    visited[y][x]=true;

    while(!q.empty()){
        pair<int,int> here= q.front();
        q.pop();

        for(int dir=0;dir<4;dir++){
            int ny=here.first+dy[dir];
            int nx=here.second+dx[dir];
            
            if(!inRange(ny,nx)) continue;
            if(!visited[ny][nx] && board[here.first][here.second]==board[ny][nx]){
                q.push(make_pair(ny,nx));
                puyo.push_back(make_pair(ny,nx));
                visited[ny][nx]=true;
            }
        }   
    }
    if(puyo.size() >=4)
        for(int i=0;i<puyo.size();i++)
            isPuyo[puyo[i].first][puyo[i].second]=true;
}
bool checkPuyo(){
    bool occured=false;
    for(int i=0;i<12;i++)
        for(int j=0;j<6;j++)
            if(isPuyo[i][j]==true){
                board[i][j]=0;
                occured=true;
            }

    if(occured==false)
        return false;

    dropPuyo();
    return true;
    
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    char val;
    for(int i=0;i<12;i++){
        for(int j=0;j<6;j++){
            cin>>val;
            if(val=='.')
                board[i][j]=0;
            else if(val=='R')
                board[i][j]=1;
            else if(val=='G')
                board[i][j]=2;
            else if(val=='B')
                board[i][j]=3;
            else if(val=='P')
                board[i][j]=4;
            else if(val=='Y')
                board[i][j]=5; 
        }
    }
    int cnt=0;
    while(1){ 
        memset(visited,false,sizeof(visited));
        memset(isPuyo,false,sizeof(isPuyo));

        for(int i=0;i<12;i++){
            for(int j=0;j<6;j++){
                if(board[i][j] && !visited[i][j])
                    bfs(i,j);
            }
        }
        if(checkPuyo())
            cnt++;
        else    break;
    }
    cout<<cnt<< '\n';
    return 0;
}