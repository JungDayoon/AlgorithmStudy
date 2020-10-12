#include <iostream>
#include <utility>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;
const int INF=97654321;
int N,M,K;
int board[650][650][3]; 
int dy[4]={0,1,0,-1};
int dx[4]={1,0,-1,0};
struct cell{
    int y,x;
    int life,state,time;
};
vector<cell> breed;

int findAnswer(){
    int result=0;
    for(int i=0;i<N+K+K;i++)
        for(int j=0;j<M+K+K;j++)
            if((board[i][j][1]== 0 && board[i][j][0]>0) || (board[i][j][1]==1 && board[i][j][0]>0))
                result++;

    return result;
}
void breeding(int y, int x,int life){
    for(int dir=0;dir<4;dir++){
        int ny=y+dy[dir];
        int nx=x+dx[dir];

        if(board[ny][nx][0]==0){//빈 곳 일 때
            cell c;
            c.y=ny, c.x=nx;
            c.life=life, c.state=0 , c.time=1;
            breed.push_back(c);
        }
    }
}
int growCells(){ //0은 생명력, 1은 상태, 2는 상태가 바뀐 시간
    int time=0;

    while(time<K){
        breed.clear();
        for(int i=0;i<N+K+K;i++){
            for(int j=0;j<M+K+K;j++){
                if(board[i][j][0]){
                    if(board[i][j][1]==1 && board[i][j][2]==1) //활성 상태이고, 시간이 1이다
                        breeding(i,j,board[i][j][0]);

                    if(board[i][j][0]==board[i][j][2]){ //생명력== 시간
                        board[i][j][1]++; //상태 바꿔줌
                        if(board[i][j][1] == 2)
                            board[i][j][2]=INF;
                        else 
                            board[i][j][2]=1;
                    }
                    else if(board[i][j][0]> board[i][j][2]) //생명력> 시간
                        board[i][j][2]++;//시간+1
                }
            }
        }
        for(int i=0;i<breed.size();i++){
            if(board[breed[i].y][breed[i].x][0] > breed[i].life)
                continue;
            board[breed[i].y][breed[i].x][0]=breed[i].life;
            board[breed[i].y][breed[i].x][1]=breed[i].state;
            board[breed[i].y][breed[i].x][2]=breed[i].time;
        }
        time++;
    }
    return findAnswer();
}
int main(){

    int TC;
    cin>>TC;
    for(int tc=1;tc<=TC;tc++){
        cin>>N>>M>>K;
        memset(board,0,sizeof(board));
        for(int i=K;i<N+K;i++)
            for(int j=K;j<M+K;j++){
                cin>>board[i][j][0];
                board[i][j][1]=0;
                board[i][j][2]=1;
            }
        
        int result=growCells();
        cout<<"#"<<tc<<" "<<result<< '\n';
    }
    return 0;
}